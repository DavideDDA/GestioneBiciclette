package com.example.GestioneBiciclette.services;

import com.example.GestioneBiciclette.models.Parcheggio;

import java.util.List;

public interface ParcheggioService {
    Parcheggio creaParcheggio(Parcheggio parcheggio);
    List<Parcheggio> listaParcheggi();
    Parcheggio modificaParcheggio(Long id, Parcheggio parcheggio);
    Parcheggio findParcheggioById(Long id);
    String deleteParcheggioById(Long id);
}
