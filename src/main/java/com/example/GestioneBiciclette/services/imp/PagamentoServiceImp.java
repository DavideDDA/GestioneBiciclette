package com.example.GestioneBiciclette.services.imp;


import com.example.GestioneBiciclette.models.Pagamento;
import com.example.GestioneBiciclette.models.Prenotazione;
import com.example.GestioneBiciclette.models.Tariffa;
import com.example.GestioneBiciclette.models.enumerated.CategoriaBicicletta;
import com.example.GestioneBiciclette.models.enumerated.TipoPagamento;
import com.example.GestioneBiciclette.repositories.PagamentoRepository;
import com.example.GestioneBiciclette.repositories.PrenotazioneRepository;
import com.example.GestioneBiciclette.repositories.TariffaRepository;
import com.example.GestioneBiciclette.services.PagamentoService;
import com.example.GestioneBiciclette.strategy.PaymentStrategy;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PagamentoServiceImp implements PagamentoService {

    @Autowired @Qualifier("effettuaPagamento")
    ObjectProvider<Pagamento> pagamentoObjectProvider;

    @Autowired
    TariffaRepository tariffaRepository;

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Autowired
    PrenotazioneRepository prenotazioneRepository;

    private final Map<TipoPagamento, PaymentStrategy> paymentStrategies;

    @Autowired
    public PagamentoServiceImp(List<PaymentStrategy> strategies){
        this.paymentStrategies = strategies.stream()
                .collect(Collectors.toMap(
                        PaymentStrategy::getTipo,
                        strategy -> strategy
                ));
    }

    @Override
    public Pagamento processaPagamento(Long prenotazioneId, TipoPagamento tipoPagamento, Double kmPercorsi) {
        Prenotazione prenotazione = prenotazioneRepository.findById(prenotazioneId).orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));
        CategoriaBicicletta categoriaBicicletta = prenotazione.getBicicletta().getCategoriaBicicletta();
        Tariffa tariffa = tariffaRepository.findByCategoria(categoriaBicicletta);

        long minuti = Duration.between(
                prenotazione.getDataInizio(),
                prenotazione.getDataFine()
        ).toMinutes();

        double totale = tariffa.calcolaCosto(minuti, kmPercorsi);

        BigDecimal importo = BigDecimal.valueOf(totale);

        PaymentStrategy strategy = paymentStrategies.get(tipoPagamento);
        if (strategy == null){
            throw new RuntimeException("Metodo di pagamento non supportato: " + tipoPagamento);
        }

        Pagamento pagamento = pagamentoObjectProvider.getObject();
        pagamento.setPrenotazione(prenotazione);
        pagamento.setMetodo(tipoPagamento);
        pagamento.setImporto(importo);

        return pagamentoRepository.save(pagamento);
    }
}
