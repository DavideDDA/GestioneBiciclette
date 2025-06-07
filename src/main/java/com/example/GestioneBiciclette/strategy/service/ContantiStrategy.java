package com.example.GestioneBiciclette.strategy.service;

import com.example.GestioneBiciclette.models.enumerated.TipoPagamento;
import com.example.GestioneBiciclette.strategy.PaymentStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ContantiStrategy implements PaymentStrategy {
    @Override
    public String processPayment(BigDecimal amount) {
        return "Pagamento in contanti effettuato per € " + amount;
    }

    @Override
    public TipoPagamento getTipo() {
        return TipoPagamento.CONTANTI;
    }
}
