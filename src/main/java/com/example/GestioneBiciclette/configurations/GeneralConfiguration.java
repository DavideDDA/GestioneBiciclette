package com.example.GestioneBiciclette.configurations;

import com.example.GestioneBiciclette.models.Bicicletta;
import com.example.GestioneBiciclette.models.Equipaggiamento;
import com.example.GestioneBiciclette.models.Parcheggio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class GeneralConfiguration {

    @Bean
    @Scope("prototype")
    public Parcheggio creaParcheggio(){
        return new Parcheggio();
    }

    @Bean
    @Scope("prototype")
    public Equipaggiamento creaEquipaggiamento(){
        return new Equipaggiamento();
    }

    @Bean
    @Scope("prototype")
    public Bicicletta creaBicicletta()  { return new Bicicletta();}
}
