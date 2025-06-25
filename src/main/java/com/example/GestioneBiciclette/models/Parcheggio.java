package com.example.GestioneBiciclette.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "parcheggi")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
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

    @OneToMany(mappedBy = "parcheggio")
    private List<Bicicletta> biciclette = new ArrayList<>();

    public Parcheggio(String nome, String citta, Integer capacita) {
        this.nome = nome;
        this.citta = citta;
        this.capacita = capacita;
    }
}
