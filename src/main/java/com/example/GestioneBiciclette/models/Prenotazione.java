package com.example.GestioneBiciclette.models;

import com.example.GestioneBiciclette.security.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private LocalDateTime dataInizio;

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

}
