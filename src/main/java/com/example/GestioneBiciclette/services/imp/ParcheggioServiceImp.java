package com.example.GestioneBiciclette.services.imp;

import com.example.GestioneBiciclette.models.Parcheggio;
import com.example.GestioneBiciclette.repositories.ParcheggioRepository;
import com.example.GestioneBiciclette.services.ParcheggioService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ParcheggioServiceImp implements ParcheggioService {

    @Autowired @Qualifier("creaParcheggio")
    ObjectProvider<Parcheggio> parcheggioObjectProvider;

    @Autowired
    ParcheggioRepository parcheggioRepository;

    @Override
    public Parcheggio creaParcheggio(Parcheggio parcheggio) {
        Parcheggio parcheggio1 = parcheggioObjectProvider.getObject();

        parcheggio1.setNome(parcheggio.getNome());
        if (parcheggio.getCapacita() < 0){
            throw new IllegalArgumentException("La capacita non puo contenere un valore negativo");
        }
        parcheggio1.setCapacita(parcheggio.getCapacita());
        parcheggio1.setCitta(parcheggio.getCitta());
        return parcheggioRepository.save(parcheggio1);
    }

    @Override
    public List<Parcheggio> listaParcheggi() {
        return parcheggioRepository.findAll();
    }

    @Override
    public Parcheggio modificaParcheggio(Long id, Parcheggio parcheggio) {
        if (!parcheggio.getId().equals(id)){
            throw new NoSuchElementException();
        }else {
            return parcheggioRepository.save(parcheggio);
        }
    }

    @Override
    public Parcheggio findParcheggioById(Long id) {
        return parcheggioRepository.findById(id).orElseThrow();
    }

    @Override
    public String deleteParcheggioById(Long id) {
        parcheggioRepository.deleteById(id);
        return "Parcheggio con id " + id + " eliminato con successo";
    }
}
