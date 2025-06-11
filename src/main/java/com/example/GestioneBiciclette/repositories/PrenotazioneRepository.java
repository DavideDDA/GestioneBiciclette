package com.example.GestioneBiciclette.repositories;

import com.example.GestioneBiciclette.models.Bicicletta;
import com.example.GestioneBiciclette.models.Prenotazione;
import com.example.GestioneBiciclette.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    Prenotazione findByUser(User user);
    Prenotazione findByBicicletta(Bicicletta bicicletta);
}
