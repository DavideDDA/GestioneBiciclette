package com.example.GestioneBiciclette.services;


import com.example.GestioneBiciclette.models.Equipaggiamento;
import com.example.GestioneBiciclette.repositories.EquipaggiamentoRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EquipaggiamentoServiceImp implements EquipaggiamentoService{

    @Autowired @Qualifier("creaEquipaggiamento")
    ObjectProvider<Equipaggiamento> equipaggiamentoObjectProvider;

    @Autowired
    EquipaggiamentoRepository equipaggiamentoRepository;


    @Override
    public Equipaggiamento creaEquipaggiamento(Equipaggiamento equipaggiamento) {
        Equipaggiamento equipaggiamento1 = equipaggiamentoObjectProvider.getObject();
        equipaggiamento1.setNome(equipaggiamento.getNome());
        equipaggiamento1.setDescrizione(equipaggiamento.getDescrizione());
        if (equipaggiamento.getPrezzo() <= 0){
            throw new IllegalArgumentException("Il prezzo non puo essere uguale a 0 o negativo!");
        }
        equipaggiamento1.setPrezzo(equipaggiamento.getPrezzo());
        return equipaggiamentoRepository.save(equipaggiamento1);
    }

    @Override
    public List<Equipaggiamento> findAllEquipaggiamenti() {
        return equipaggiamentoRepository.findAll();
    }

    @Override
    public Equipaggiamento findByIdEquipaggiamento(Long id) {
        return equipaggiamentoRepository.findById(id).orElseThrow();
    }

    @Override
    public String deleteEquipaggiamento(Long id) {
        equipaggiamentoRepository.deleteById(id);
        return "Equipaggiamento eliminato";
    }

    @Override
    public Equipaggiamento modificaEquipaggiamento(Long id, Equipaggiamento equipaggiamento) {
        if (!equipaggiamento.getId().equals(id)){
            throw new NoSuchElementException();
        }else {
            return equipaggiamentoRepository.save(equipaggiamento);
        }
    }
}
