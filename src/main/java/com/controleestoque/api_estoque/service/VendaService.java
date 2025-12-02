package com.controleestoque.api_estoque.service;

import com.controleestoque.api_estoque.dto.VendaRequestDTO;
import com.controleestoque.api_estoque.exception.EstoqueInsuficienteException;
import com.controleestoque.api_estoque.model.*;
import com.controleestoque.api_estoque.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional
public class VendaService {
    
    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;
    private final ItemVendaRepository itemVendaRepository;
    
    public Venda registrarVenda(VendaRequestDTO vendaRequest) {
        // 1. Buscar cliente
        Cliente cliente = clienteRepository.findById(vendaRequest.getClienteId())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + vendaRequest.getClienteId()));
        
        // 2. Criar venda
        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setDataVenda(LocalDateTime.now());
        venda.setItens(new ArrayList<>());
        
        // 3. Validar e processar cada item
        for (var itemRequest : vendaRequest.getItens()) {
            Produto produto = produtoRepository.findById(itemRequest.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + itemRequest.getProdutoId()));
            
            Estoque estoque = produto.getEstoque();
            if (estoque == null) {
                throw new RuntimeException("Produto não possui registro de estoque");
            }
            
            // Verificar estoque
            if (estoque.getQuantidade() < itemRequest.getQuantidade()) {
                throw new EstoqueInsuficienteException(
                    produto.getNome(),
                    estoque.getQuantidade(),
                    itemRequest.getQuantidade()
                );
            }
            
            // Atualizar estoque
            estoque.setQuantidade(estoque.getQuantidade() - itemRequest.getQuantidade());
            estoqueRepository.save(estoque);
            
            // Criar item da venda
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setVenda(venda);
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(itemRequest.getQuantidade());
            itemVenda.setPrecoUnitario(produto.getPreco());
            
            venda.getItens().add(itemVenda);
        }
        
        // 4. Salvar venda e itens
        Venda vendaSalva = vendaRepository.save(venda);
        
        // 5. Salvar cada item (cascata deve lidar com isso, mas garantindo)
        for (ItemVenda item : venda.getItens()) {
            itemVendaRepository.save(item);
        }
        
        return vendaSalva;
    }
}
