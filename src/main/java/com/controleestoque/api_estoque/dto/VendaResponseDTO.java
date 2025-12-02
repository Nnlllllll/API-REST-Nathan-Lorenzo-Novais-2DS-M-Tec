package com.controleestoque.api_estoque.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaResponseDTO {
    private Long id;
    private LocalDateTime dataVenda;
    private Long clienteId;
    private String clienteNome;
    private List<ItemVendaResponseDTO> itens;
    private Double valorTotal;
}
