package com.example.GestioneBiciclette.services;

import com.example.GestioneBiciclette.models.Pagamento;
import com.example.GestioneBiciclette.models.enumerated.CategoriaBicicletta;
import com.example.GestioneBiciclette.models.enumerated.TipoPagamento;

import java.math.BigDecimal;

public interface PagamentoService {

    Pagamento processaPagamento(Long prenotazioneId, TipoPagamento tipoPagamento, Double kmPercorsi);
}
