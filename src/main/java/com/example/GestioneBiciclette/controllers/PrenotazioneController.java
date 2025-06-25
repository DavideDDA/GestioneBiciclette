package com.example.GestioneBiciclette.controllers;


import com.example.GestioneBiciclette.DTO.PrenotazioneDTO;
import com.example.GestioneBiciclette.models.Bicicletta;
import com.example.GestioneBiciclette.models.Prenotazione;
import com.example.GestioneBiciclette.security.entity.User;
import com.example.GestioneBiciclette.security.repository.UserRepository;
import com.example.GestioneBiciclette.services.BiciclettaService;
import com.example.GestioneBiciclette.services.ParcheggioService;
import com.example.GestioneBiciclette.services.PrenotazioneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    PrenotazioneService prenotazioneService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BiciclettaService biciclettaService;

    @Autowired
    ParcheggioService parcheggioService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Prenotazione>> getAllPrenotazioni(){
        return ResponseEntity.ok(prenotazioneService.findAllPrenotazioni());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Prenotazione> getPrenotazioneById(@PathVariable Long id){
        return ResponseEntity.ok(prenotazioneService.findPrenotazioneById(id));
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Prenotazione> getPrenotazioneByUser(@RequestBody User user){
        return ResponseEntity.ok(prenotazioneService.findPrenotazioneByUser(user));
    }

    @GetMapping("/bicicletta")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Prenotazione> getPrenotazioneByBicicletta(@RequestBody Bicicletta bicicletta){
        return ResponseEntity.ok(prenotazioneService.findByBicicletta(bicicletta));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Prenotazione> creaPrenotazione(@Valid @RequestBody PrenotazioneDTO prenotazioneDTO){
        return ResponseEntity.ok(prenotazioneService.creaPrenotazione(prenotazioneDTO));
    }
}
