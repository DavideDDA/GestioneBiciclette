package com.example.GestioneBiciclette.runners;


import com.example.GestioneBiciclette.models.Bicicletta;
import com.example.GestioneBiciclette.models.enumerated.CategoriaBicicletta;
import com.example.GestioneBiciclette.models.Equipaggiamento;
import com.example.GestioneBiciclette.models.Parcheggio;
import com.example.GestioneBiciclette.services.BiciclettaService;
import com.example.GestioneBiciclette.services.EquipaggiamentoService;
import com.example.GestioneBiciclette.services.ParcheggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

    @Override
    public void run(String... args) throws Exception {
        List<Parcheggio> parcheggi = parcheggioService.listaParcheggi();
        List<Equipaggiamento> equipaggiamenti = equipaggiamentoService.findAllEquipaggiamenti();
        List<Bicicletta> biciclette = biciclettaService.findAllBiciclette();

        if (parcheggi.isEmpty()){
            creaParcheggio();
        }

        if (equipaggiamenti.isEmpty()){
            generaEquipaggiamento();
        }

        if (biciclette.isEmpty()){
            creaBici();
        }

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
}
