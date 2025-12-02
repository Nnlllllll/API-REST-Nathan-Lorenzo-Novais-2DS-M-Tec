package com.controleestoque.api_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.controleestoque.api_estoque.model.Venda;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findByClienteId(Long clienteId);
    
    @Query("SELECT v FROM Venda v JOIN FETCH v.itens WHERE v.id = :id")
    Venda findByIdWithItens(@Param("id") Long id);
}
