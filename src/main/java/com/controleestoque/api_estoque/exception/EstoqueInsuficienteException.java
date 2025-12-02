package com.controleestoque.api_estoque.exception;

public class EstoqueInsuficienteException extends RuntimeException {
    
    public EstoqueInsuficienteException(String message) {
        super(message);
    }
    
    public EstoqueInsuficienteException(String produto, Integer disponivel, Integer solicitado) {
        super(String.format("Estoque insuficiente para produto '%s'. Disponível: %d, Solicitado: %d", 
            produto, disponivel, solicitado));
    }
}
