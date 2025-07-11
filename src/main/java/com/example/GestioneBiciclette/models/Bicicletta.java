package com.example.GestioneBiciclette.models;

import com.example.GestioneBiciclette.models.enumerated.CategoriaBicicletta;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    @Column(name = "chilometri_totali")
    private Double chilometriTotali = 0.0;

    @Column(name = "numero_noleggi")
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
