package com.example.GestioneBiciclette.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "parcheggi")
public class Parcheggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String citta;

    @Column(nullable = false)
    private Integer capacita;

    public Parcheggio(String nome, String citta, Integer capacita) {
        this.nome = nome;
        this.citta = citta;
        this.capacita = capacita;
    }
}
