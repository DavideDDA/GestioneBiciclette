package com.example.GestioneBiciclette.services.imp;


import com.example.GestioneBiciclette.models.Bicicletta;
import com.example.GestioneBiciclette.models.Pagamento;
import com.example.GestioneBiciclette.models.Parcheggio;
import com.example.GestioneBiciclette.models.Prenotazione;
import com.example.GestioneBiciclette.repositories.BiciclettaRepository;
import com.example.GestioneBiciclette.repositories.PrenotazioneRepository;
import com.example.GestioneBiciclette.security.entity.User;
import com.example.GestioneBiciclette.services.PrenotazioneService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PrenotazioneServiceImp implements PrenotazioneService {

    //Istanzio dinamicamente le prenotazioni
    @Autowired @Qualifier("effettuaPrenotazione")
    ObjectProvider<Prenotazione> prenotazioneObjectProvider;

    @Autowired
    PrenotazioneRepository prenotazioneRepository;

    @Autowired
    BiciclettaRepository biciclettaRepository;

    @Override
    public List<Prenotazione> findAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    @Override
    public Prenotazione findPrenotazioneById(Long id) {
        return prenotazioneRepository.findById(id).orElseThrow();
    }

    @Override
    public Prenotazione findPrenotazioneByUser(User user) {
        return prenotazioneRepository.findByUser(user);
    }

    @Override
    public Prenotazione findByBicicletta(Bicicletta bicicletta) {
        return prenotazioneRepository.findByBicicletta(bicicletta);
    }

    @Override
    public Prenotazione creaPrenotazione(LocalDateTime dataFine, User user, Bicicletta bicicletta, Parcheggio parcheggioArrivo) {
        if (!bicicletta.getDisponibile()){
            throw new IllegalStateException("La bicicletta selezionata non e disponibile");
        }
        Prenotazione prenotazione = prenotazioneObjectProvider.getObject();
        prenotazione.setDataInizio(LocalDateTime.now());
        prenotazione.setDataFine(dataFine);
        prenotazione.setUser(user);
        prenotazione.setBicicletta(bicicletta);
        prenotazione.setParcheggioPartenza(bicicletta.getParcheggio());
        prenotazione.setParcheggioArrivo(parcheggioArrivo);
        bicicletta.setDisponibile(false);
        biciclettaRepository.save(bicicletta);
        return prenotazioneRepository.save(prenotazione);
    }
}
