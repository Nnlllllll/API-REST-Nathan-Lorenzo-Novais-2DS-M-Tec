package com.controleestoque.api_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.controleestoque.api_estoque.model.ItemVenda;
import java.util.List;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
    List<ItemVenda> findByVendaId(Long vendaId);
    List<ItemVenda> findByProdutoId(Long produtoId);
}
