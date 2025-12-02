package com.controleestoque.api_estoque.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaRequestDTO {
    
    @NotNull(message = "ID do cliente é obrigatório")
    private Long clienteId;
    
    @NotEmpty(message = "A venda deve ter pelo menos um item")
    private List<ItemVendaRequestDTO> itens;
}
