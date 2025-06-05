package com.example.GestioneBiciclette.strategy;

import com.example.GestioneBiciclette.models.enumerated.TipoPagamento;

import java.math.BigDecimal;

public interface PaymentStrategy {
    String processPayment(BigDecimal amount);
    TipoPagamento getTipo();
}
