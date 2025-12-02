package com.controleestoque.api_estoque.controller;

import com.controleestoque.api_estoque.dto.VendaRequestDTO;
import com.controleestoque.api_estoque.dto.VendaResponseDTO;
import com.controleestoque.api_estoque.dto.ItemVendaResponseDTO;
import com.controleestoque.api_estoque.model.Venda;
import com.controleestoque.api_estoque.model.ItemVenda;
import com.controleestoque.api_estoque.service.VendaService;
import com.controleestoque.api_estoque.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService vendaService;
    private final VendaRepository vendaRepository;

    @GetMapping
    public List<VendaResponseDTO> getAllVendas() {
        return vendaRepository.findAll().stream()
                .map(this::toVendaResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaResponseDTO> getVendaById(@PathVariable Long id) {
        return vendaRepository.findById(id)
                .map(this::toVendaResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> registrarVenda(@Valid @RequestBody VendaRequestDTO vendaRequest) {
        try {
            Venda venda = vendaService.registrarVenda(vendaRequest);
            VendaResponseDTO response = toVendaResponseDTO(venda);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private VendaResponseDTO toVendaResponseDTO(Venda venda) {
        List<ItemVendaResponseDTO> itensDTO = venda.getItens().stream()
                .map(this::toItemVendaResponseDTO)
                .collect(Collectors.toList());
        
        return new VendaResponseDTO(
            venda.getId(),
            venda.getDataVenda(),
            venda.getCliente().getId(),
            venda.getCliente().getNome(),
            itensDTO,
            venda.getValorTotal()
        );
    }
    
    private ItemVendaResponseDTO toItemVendaResponseDTO(ItemVenda item) {
        return new ItemVendaResponseDTO(
            item.getProduto().getId(),
            item.getProduto().getNome(),
            item.getQuantidade(),
            item.getPrecoUnitario(),
            item.getSubtotal()
        );
    }
}
