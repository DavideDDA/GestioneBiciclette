package com.example.GestioneBiciclette.services;

import com.example.GestioneBiciclette.models.Equipaggiamento;

import java.util.List;

public interface EquipaggiamentoService {

    Equipaggiamento creaEquipaggiamento(Equipaggiamento equipaggiamento);
    List<Equipaggiamento> findAllEquipaggiamenti();
    Equipaggiamento findByIdEquipaggiamento(Long id);
    String deleteEquipaggiamento(Long id);
    Equipaggiamento modificaEquipaggiamento(Long id, Equipaggiamento equipaggiamento);
}
