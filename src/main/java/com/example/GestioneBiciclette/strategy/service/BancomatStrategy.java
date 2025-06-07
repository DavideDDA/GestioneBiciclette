package com.example.GestioneBiciclette.strategy.service;

import com.example.GestioneBiciclette.models.enumerated.TipoPagamento;
import com.example.GestioneBiciclette.strategy.PaymentStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BancomatStrategy implements PaymentStrategy {
    @Override
    public String processPayment(BigDecimal amount) {
        return "Pagamento con bancomat effettuato per € " + amount;
    }

    @Override
    public TipoPagamento getTipo() {
        return TipoPagamento.BANCOMAT;
    }
}
