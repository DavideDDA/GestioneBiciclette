package com.example.GestioneBiciclette.services.imp;


import com.example.GestioneBiciclette.DTO.PrenotazioneDTO;
import com.example.GestioneBiciclette.models.Bicicletta;
import com.example.GestioneBiciclette.models.Parcheggio;
import com.example.GestioneBiciclette.models.Prenotazione;
import com.example.GestioneBiciclette.repositories.BiciclettaRepository;
import com.example.GestioneBiciclette.repositories.PrenotazioneRepository;
import com.example.GestioneBiciclette.security.entity.User;
import com.example.GestioneBiciclette.security.repository.UserRepository;
import com.example.GestioneBiciclette.services.BiciclettaService;
import com.example.GestioneBiciclette.services.ParcheggioService;
import com.example.GestioneBiciclette.services.PrenotazioneService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PrenotazioneServiceImp implements PrenotazioneService {

    //Istanzio dinamicamente le prenotazioni
    @Autowired @Qualifier("effettuaPrenotazione")
    ObjectProvider<Prenotazione> prenotazioneObjectProvider;

    @Autowired
    PrenotazioneRepository prenotazioneRepository;

    @Autowired
    BiciclettaRepository biciclettaRepository;

    @Autowired
    BiciclettaService biciclettaService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ParcheggioService parcheggioService;

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
    @Transactional
    public Prenotazione creaPrenotazione(PrenotazioneDTO prenotazioneDTO) {
        // Recupero entità
        User user = userRepository.findById(prenotazioneDTO.getUserId())
                .orElseThrow();

        Bicicletta bicicletta = biciclettaService.findBiciclettaById(prenotazioneDTO.getBiciclettaId());
        Parcheggio parcheggioArrivo = parcheggioService.findParcheggioById(prenotazioneDTO.getParcheggioArrivoId());

        // Validazioni business
        if (!bicicletta.getDisponibile()) {
            throw new IllegalStateException("La bicicletta selezionata non è disponibile");
        }

        // Creazione prenotazione
        Prenotazione prenotazione = prenotazioneObjectProvider.getObject();
        prenotazione.setDataInizio(LocalDateTime.now());
        if (prenotazioneDTO.getDataFine().isBefore(prenotazione.getDataInizio()) || prenotazioneDTO.getDataFine().isEqual(prenotazione.getDataInizio())) {
            throw new IllegalArgumentException("La data di fine deve essere successiva all'orario corrente");
        }
        prenotazione.setDataFine(prenotazioneDTO.getDataFine());
        prenotazione.setUser(user);
        prenotazione.setBicicletta(bicicletta);
        prenotazione.setParcheggioPartenza(bicicletta.getParcheggio());
        prenotazione.setParcheggioArrivo(parcheggioArrivo);
        prenotazione.setCodicePrenotazione(generaCodice());

        // Aggiorna disponibilità
        bicicletta.setDisponibile(false);
        biciclettaRepository.save(bicicletta);

        return prenotazioneRepository.save(prenotazione);
    }

    private String generaCodice() {
        return UUID.randomUUID().toString().substring(0, 9).toUpperCase();
    }
}
