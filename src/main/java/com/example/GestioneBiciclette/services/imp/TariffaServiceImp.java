package com.example.GestioneBiciclette.services.imp;


import com.example.GestioneBiciclette.models.enumerated.CategoriaBicicletta;
import com.example.GestioneBiciclette.models.Tariffa;
import com.example.GestioneBiciclette.repositories.TariffaRepository;
import com.example.GestioneBiciclette.services.TariffaService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TariffaServiceImp implements TariffaService {

    @Autowired @Qualifier("creaTariffa")
    ObjectProvider<Tariffa> tariffaObjectProvider;

    @Autowired
    TariffaRepository tariffaRepository;

    @Override
    public Tariffa creaTariffa(Tariffa tariffaPass) {
        Tariffa tariffa = tariffaObjectProvider.getObject();
        tariffa.setCategoria(tariffaPass.getCategoria());
        if (tariffaPass.getPrezzoOrario() > 0 && tariffaPass.getPrezzoPerKm() > 0){
            tariffa.setPrezzoOrario(tariffaPass.getPrezzoOrario());
            tariffa.setPrezzoPerKm(tariffaPass.getPrezzoPerKm());
        }else {
            throw new IllegalArgumentException("Il prezzo orario e il prezzo per km devono essere > 0");
        }
        return tariffaRepository.save(tariffa);
    }

    @Override
    public String deleteTariffa(Long id) {
        Tariffa tariffaTrov = tariffaRepository.findById(id).orElseThrow();
        tariffaRepository.delete(tariffaTrov);
        return "Tariffa con id " + id +  " eliminata";
    }

    @Override
    public List<Tariffa> findAllTariffe() {
        return tariffaRepository.findAll();
    }

    @Override
    public Tariffa modificaTariffa(Long id, Tariffa tariffa) {
        if (!tariffa.getId().equals(id)){
            throw new NoSuchElementException("Nessuna tariffa con quell'id");
        }else if (tariffa.getPrezzoOrario() > 0 && tariffa.getPrezzoPerKm() > 0){
            return tariffaRepository.save(tariffa);
        }else {
            throw new IllegalArgumentException("Il prezzo orario e il prezzo per km devono essere > 0");
        }
    }

    @Override
    public Tariffa findByCategoria(CategoriaBicicletta categoriaBicicletta) {
        return tariffaRepository.findByCategoria(categoriaBicicletta);
    }

    @Override
    public Tariffa findById(Long id) {
        return tariffaRepository.findById(id).orElseThrow();
    }
}
