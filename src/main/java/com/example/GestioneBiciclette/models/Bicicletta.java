package com.example.GestioneBiciclette.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "biciclette")
public class Bicicletta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codice_identificativo", unique = true, nullable = false)
    private String codiceIdentificativo;

    private Boolean disponibile = true;

    @Enumerated(EnumType.STRING)
    private CategoriaBicicletta categoriaBicicletta;

    @ManyToOne
    @JoinColumn(name = "id_parcheggio")
    private Parcheggio parcheggio;

    private Double chilometriTotali = 0.0;

    private Integer numeroNoleggi = 0;

    @ManyToMany
    @JoinTable(
            name = "bicicletta_equipaggiamento",
            joinColumns = @JoinColumn(name = "bicicletta_id"),
            inverseJoinColumns = @JoinColumn(name = "equipaggiamento_id")
    )
    private Set<Equipaggiamento> equipaggiamenti = new HashSet<>();

    public Bicicletta(CategoriaBicicletta categoriaBicicletta, Parcheggio parcheggio, Set<Equipaggiamento> equipaggiamenti) {
        this.categoriaBicicletta = categoriaBicicletta;
        this.parcheggio = parcheggio;
        this.equipaggiamenti = equipaggiamenti;
    }
}
