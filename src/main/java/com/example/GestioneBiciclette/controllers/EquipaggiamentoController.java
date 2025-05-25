package com.example.GestioneBiciclette.controllers;


import com.example.GestioneBiciclette.models.Equipaggiamento;
import com.example.GestioneBiciclette.services.EquipaggiamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/equipaggiamento")
public class EquipaggiamentoController {

    @Autowired
    EquipaggiamentoService equipaggiamentoService;

    @GetMapping
    public ResponseEntity<List<Equipaggiamento>> getAllEquipaggiamenti(){
        return ResponseEntity.ok(equipaggiamentoService.findAllEquipaggiamenti());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipaggiamento> getEquipaggiamentoById(@PathVariable Long id){
        return ResponseEntity.ok(equipaggiamentoService.findByIdEquipaggiamento(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Equipaggiamento> aggiungiEquipaggiamento(@RequestBody Equipaggiamento equipaggiamento){
        return ResponseEntity.ok(equipaggiamentoService.creaEquipaggiamento(equipaggiamento));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteEquipaggiamentoById(@PathVariable Long id){
        return ResponseEntity.ok(equipaggiamentoService.deleteEquipaggiamento(id));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Equipaggiamento> modificaEquipaggiamento(@PathVariable Long id, Map<String, Object> updates){
        Equipaggiamento equipaggiamento = equipaggiamentoService.findByIdEquipaggiamento(id);
        updates.forEach((key, value) -> {
            Field field  = ReflectionUtils.findField(Equipaggiamento.class, key);
            if (field != null){
                field.setAccessible(true);
                ReflectionUtils.setField(field, equipaggiamento, value);
            }
        });
        return ResponseEntity.ok(equipaggiamentoService.modificaEquipaggiamento(id, equipaggiamento));
    }


}
