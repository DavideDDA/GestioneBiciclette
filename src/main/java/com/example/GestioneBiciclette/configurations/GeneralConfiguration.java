package com.example.GestioneBiciclette.configurations;

import com.example.GestioneBiciclette.models.*;
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
    public Tariffa creaTariffa(){ return new Tariffa();}

    @Bean
    @Scope("prototype")
    public Pagamento effettuaPagamento() { return new Pagamento();}

    @Bean
    @Scope("prototype")
    public Prenotazione effettuaPrenotazione() { return new Prenotazione(); }
}
