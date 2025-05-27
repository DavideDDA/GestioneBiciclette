package com.example.GestioneBiciclette.factory;


import com.example.GestioneBiciclette.models.Bicicletta;
import com.example.GestioneBiciclette.models.CategoriaBicicletta;
import com.example.GestioneBiciclette.models.Equipaggiamento;
import com.example.GestioneBiciclette.models.Parcheggio;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
public class BiciclettaFactory {
    public Bicicletta creaBicicletta(CategoriaBicicletta categoria,
                                     Parcheggio parcheggio,
                                     Set<Equipaggiamento> equipaggiamenti) {
        Bicicletta bici = new Bicicletta();
        bici.setCategoriaBicicletta(categoria);
        bici.setParcheggio(parcheggio);
        bici.setEquipaggiamenti(equipaggiamenti);
        bici.setCodiceIdentificativo(generaCodice());
        return bici;
    }

    private String generaCodice() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
