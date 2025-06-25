package com.example.GestioneBiciclette.services;

import com.example.GestioneBiciclette.DTO.PrenotazioneDTO;
import com.example.GestioneBiciclette.models.Bicicletta;
import com.example.GestioneBiciclette.models.Pagamento;
import com.example.GestioneBiciclette.models.Parcheggio;
import com.example.GestioneBiciclette.models.Prenotazione;
import com.example.GestioneBiciclette.security.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface PrenotazioneService {

    List<Prenotazione> findAllPrenotazioni();
    Prenotazione findPrenotazioneById(Long id);
    Prenotazione findPrenotazioneByUser(User user);
    Prenotazione findByBicicletta(Bicicletta bicicletta);
    Prenotazione creaPrenotazione(PrenotazioneDTO prenotazioneDTO);
}
