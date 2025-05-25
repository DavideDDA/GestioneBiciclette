package com.example.GestioneBiciclette.controllers;


import com.example.GestioneBiciclette.models.Parcheggio;
import com.example.GestioneBiciclette.services.ParcheggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/parcheggio")
public class ParcheggioController {

    @Autowired
    ParcheggioService parcheggioService;

    @GetMapping
    public ResponseEntity<List<Parcheggio>> findAllParcheggi(){
        return ResponseEntity.ok(parcheggioService.listaParcheggi());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parcheggio> findParcheggioById(@PathVariable Long id){
        return ResponseEntity.ok(parcheggioService.findParcheggioById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Parcheggio> aggiungiParcheggio(@RequestBody Parcheggio parcheggio){
        return ResponseEntity.ok(parcheggioService.creaParcheggio(parcheggio));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteParcheggioById(@PathVariable Long id){
        return ResponseEntity.ok(parcheggioService.deleteParcheggioById(id));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Parcheggio> modificaParcheggio(@PathVariable Long id, Map<String, Object> updates){
        Parcheggio parcheggioTrov = parcheggioService.findParcheggioById(id);
        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Parcheggio.class, key);
            if (field != null){
                field.setAccessible(true);
                ReflectionUtils.setField(field, parcheggioTrov, value);
            }
        });
        return ResponseEntity.ok(parcheggioService.modificaParcheggio(id, parcheggioTrov));
    }

}
