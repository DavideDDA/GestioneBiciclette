package com.example.GestioneBiciclette.services;

import com.example.GestioneBiciclette.models.Bicicletta;

import java.util.List;

public interface BiciclettaService {
    Bicicletta creaBicicletta(Bicicletta bicicletta);
    String eliminaBicicletta(Long id);
    List<Bicicletta> findAllBiciclette();
    Bicicletta findBiciclettaById(Long id);
    Bicicletta modificaBicicletta(Long id, Bicicletta bicicletta);
}
