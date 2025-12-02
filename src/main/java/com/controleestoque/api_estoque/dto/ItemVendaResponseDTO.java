package com.controleestoque.api_estoque.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemVendaResponseDTO {
    private Long produtoId;
    private String produtoNome;
    private Integer quantidade;
    private Double precoUnitario;
    private Double subtotal;
}
