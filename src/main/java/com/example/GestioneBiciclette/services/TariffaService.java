package com.example.GestioneBiciclette.services;

import com.example.GestioneBiciclette.models.CategoriaBicicletta;
import com.example.GestioneBiciclette.models.Tariffa;

import java.util.List;

public interface TariffaService {
    Tariffa creaTariffa(Tariffa tariffa);
    String deleteTariffa(Long id);
    List<Tariffa> findAllTariffe();
    Tariffa modificaTariffa(Long id,Tariffa tariffa);
    Tariffa findByCategoria(CategoriaBicicletta categoriaBicicletta);
    Tariffa findById(Long  id);
}
