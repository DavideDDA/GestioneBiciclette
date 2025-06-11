package com.example.GestioneBiciclette.services;

import com.example.GestioneBiciclette.models.Pagamento;
import com.example.GestioneBiciclette.models.enumerated.TipoPagamento;

import java.util.List;


public interface PagamentoService {

    Pagamento processaPagamento(Long prenotazioneId, TipoPagamento tipoPagamento, Double kmPercorsi);
    Pagamento findById(Long id);
    List<Pagamento> findAllPagamenti();
}
