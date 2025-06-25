package com.example.GestioneBiciclette.models;

import com.example.GestioneBiciclette.security.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codice_prenotazione", nullable = false, unique = true)
    private String codicePrenotazione;

    private LocalDateTime dataInizio = LocalDateTime.now();

    private LocalDateTime dataFine;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bicicletta_id")
    private Bicicletta bicicletta;

    @ManyToOne
    @JoinColumn(name = "parcheggio_partenza_id")
    private Parcheggio parcheggioPartenza;

    @ManyToOne
    @JoinColumn(name = "parcheggio_arrivo_id")
    private Parcheggio parcheggioArrivo;

    @OneToOne(mappedBy = "prenotazione", cascade = CascadeType.ALL)
    private Pagamento pagamento;

    public Double calcolaKm(Prenotazione prenotazione){
        long minuti = Duration.between(
                prenotazione.getDataInizio(),
                prenotazione.getDataFine()
        ).toMinutes();
        double kmPerMinuto = 0.1;
        return kmPerMinuto * minuti;
    }

    public Prenotazione(LocalDateTime dataFine, User user, Bicicletta bicicletta, Parcheggio parcheggioArrivo) {
        this.dataFine = dataFine;
        this.user = user;
        this.bicicletta = bicicletta;
        this.parcheggioArrivo = parcheggioArrivo;
    }
}
