package com.example.GestioneBiciclette.controllers;


import com.example.GestioneBiciclette.models.CategoriaBicicletta;
import com.example.GestioneBiciclette.models.Tariffa;
import com.example.GestioneBiciclette.services.TariffaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tariffe")
public class TariffaController {

    @Autowired
    TariffaService tariffaService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Tariffa>> getAllTariffe(){
        return ResponseEntity.ok(tariffaService.findAllTariffe());
    }

    @GetMapping("/{categoria}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Tariffa> findByCategoria(@PathVariable("categoria") CategoriaBicicletta categoriaBicicletta){
        return ResponseEntity.ok(tariffaService.findByCategoria(categoriaBicicletta));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Tariffa> creaTariffa(@RequestBody Tariffa tariffa){
        return ResponseEntity.ok(tariffaService.creaTariffa(tariffa));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTariffa(@PathVariable Long id){
        return ResponseEntity.ok(tariffaService.deleteTariffa(id));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Tariffa> aggiornaTariffa(@PathVariable Long id, Map<String, Object> updates){
        Tariffa tariffaTrov = tariffaService.findById(id);
        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Tariffa.class, key);
            if (field != null){
                field.setAccessible(true);
                ReflectionUtils.setField(field, tariffaTrov, value);
            }
        });
        return ResponseEntity.ok(tariffaService.modificaTariffa(id, tariffaTrov));
    }
}
