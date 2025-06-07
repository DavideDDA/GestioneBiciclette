package com.example.GestioneBiciclette.strategy.service;

import com.example.GestioneBiciclette.models.enumerated.TipoPagamento;
import com.example.GestioneBiciclette.strategy.PaymentStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartaDiCreditoStrategy implements PaymentStrategy {
    @Override
    public String processPayment(BigDecimal amount) {
        return "Pagamento con carta di credito effettuato per €" + amount;
    }

    @Override
    public TipoPagamento getTipo() {
        return TipoPagamento.CARTA_DI_CREDITO;
    }
}
