package com.example.GestioneBiciclette.controllers;

import com.example.GestioneBiciclette.models.Bicicletta;
import com.example.GestioneBiciclette.services.BiciclettaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bicicletta")
public class BiciclettaController {

    @Autowired
    BiciclettaService biciclettaService;

    @GetMapping
    public ResponseEntity<List<Bicicletta>> getAllBiciclette(){
        return ResponseEntity.ok(biciclettaService.findAllBiciclette());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bicicletta> getBiciclettaById(@PathVariable Long id){
        return ResponseEntity.ok(biciclettaService.findBiciclettaById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Bicicletta> creaBicicletta(@RequestBody Bicicletta bicicletta){
        return ResponseEntity.ok(biciclettaService.creaBicicletta(bicicletta));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteBiciclettaById(@PathVariable Long id){
        return ResponseEntity.ok(biciclettaService.eliminaBicicletta(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Bicicletta> modificaBicicletta(@PathVariable Long id, @RequestBody Bicicletta bicicletta){
        return ResponseEntity.ok(biciclettaService.modificaBicicletta(id, bicicletta));
    }
}
