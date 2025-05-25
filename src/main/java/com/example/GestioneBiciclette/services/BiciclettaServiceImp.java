package com.example.GestioneBiciclette.services;

import com.example.GestioneBiciclette.models.Bicicletta;
import com.example.GestioneBiciclette.repositories.BiciclettaRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class BiciclettaServiceImp implements BiciclettaService{

    @Autowired @Qualifier("creaBicicletta")
    ObjectProvider<Bicicletta> biciclettaObjectProvider;

    @Autowired
    BiciclettaRepository biciclettaRepository;


    @Override
    public Bicicletta creaBicicletta(Bicicletta bicicletta) {
        Bicicletta bici = biciclettaObjectProvider.getObject();
        bici.setCategoriaBicicletta(bicicletta.getCategoriaBicicletta());
        bici.setParcheggio(bicicletta.getParcheggio());
        bici.setEquipaggiamenti(bicicletta.getEquipaggiamenti());
        bici.setCodiceIdentificativo(generaCodice());
        return biciclettaRepository.save(bici);
    }

    @Override
    public String eliminaBicicletta(Long id) {
        biciclettaRepository.deleteById(id);
        return "Bicicletta con id " + id + " eliminata";
    }

    @Override
    public List<Bicicletta> findAllBiciclette() {
        return biciclettaRepository.findAll();
    }

    @Override
    public Bicicletta findBiciclettaById(Long id) {
        return biciclettaRepository.findById(id).orElseThrow();
    }

    @Override
    public Bicicletta modificaBicicletta(Long id, Bicicletta bicicletta) {
        if (!bicicletta.getId().equals(id)){
            throw new NoSuchElementException();
        }else {
            return biciclettaRepository.save(bicicletta);
        }
    }

    private String generaCodice() {
        return "BICI-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
