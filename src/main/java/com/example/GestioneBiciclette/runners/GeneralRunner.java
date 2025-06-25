package com.example.GestioneBiciclette.runners;


import com.example.GestioneBiciclette.models.Bicicletta;
import com.example.GestioneBiciclette.models.Prenotazione;
import com.example.GestioneBiciclette.models.enumerated.CategoriaBicicletta;
import com.example.GestioneBiciclette.models.Equipaggiamento;
import com.example.GestioneBiciclette.models.Parcheggio;
import com.example.GestioneBiciclette.security.entity.User;
import com.example.GestioneBiciclette.security.repository.UserRepository;
import com.example.GestioneBiciclette.services.BiciclettaService;
import com.example.GestioneBiciclette.services.EquipaggiamentoService;
import com.example.GestioneBiciclette.services.ParcheggioService;
import com.example.GestioneBiciclette.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class GeneralRunner implements CommandLineRunner {

    @Autowired
    ParcheggioService parcheggioService;

    @Autowired
    EquipaggiamentoService equipaggiamentoService;

    @Autowired
    BiciclettaService biciclettaService;

    @Autowired
    PrenotazioneService prenotazioneService;

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Parcheggio> parcheggi = parcheggioService.listaParcheggi();
        List<Equipaggiamento> equipaggiamenti = equipaggiamentoService.findAllEquipaggiamenti();
        List<Bicicletta> biciclette = biciclettaService.findAllBiciclette();
        List<Prenotazione> prenotazioni = prenotazioneService.findAllPrenotazioni();

        if (parcheggi.isEmpty()){
            creaParcheggio();
        }

        if (equipaggiamenti.isEmpty()){
            generaEquipaggiamento();
        }

        if (biciclette.isEmpty()){
            creaBici();
        }

/*        if (prenotazioni.isEmpty()){
            creaPrenotazione();
        }*/

    }


    public void creaParcheggio(){
        Parcheggio parcheggio = new Parcheggio("Test", "Napoli", 30);
        parcheggioService.creaParcheggio(parcheggio);
    }

    public void generaEquipaggiamento(){
        Equipaggiamento equipaggiamento = new Equipaggiamento("Casco", "Casco per proteggere da infortuni", 9.99);
        equipaggiamentoService.creaEquipaggiamento(equipaggiamento);
    }

    public void creaBici(){
        Set<Equipaggiamento> equipaggiamenti = new HashSet<>();
        equipaggiamenti.add(equipaggiamentoService.findByIdEquipaggiamento(1L));
        Bicicletta bici = new Bicicletta(CategoriaBicicletta.CORSA, parcheggioService.findParcheggioById(1L), equipaggiamenti);
        biciclettaService.creaBicicletta(bici);
    }
/*    public void creaPrenotazione(){
        LocalDateTime fine = LocalDateTime.of(2025, 06 , 24 , 16 , 50);
        User user = userRepository.findById(1L).orElseThrow();
        Bicicletta bicicletta = biciclettaService.findBiciclettaById(1L);
        Parcheggio parcheggio = parcheggioService.findParcheggioById(1L);
        prenotazioneService.creaPrenotazione(fine, user, bicicletta, parcheggio);
    }*/
}
