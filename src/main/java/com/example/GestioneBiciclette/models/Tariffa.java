package com.example.GestioneBiciclette.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tariffe")
public class Tariffa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoriaBicicletta categoria;

    private Double prezzoOrario;
    
    private Double prezzoPerKm;

    // Metodo per calcolare il costo totale
    public Double calcolaCosto(int minuti, double chilometri) {
        double ore = minuti / 60.0;
        return (ore * prezzoOrario) + (chilometri * prezzoPerKm);
    }

    public Tariffa(CategoriaBicicletta categoria, Double prezzoOrario, Double prezzoPerKm) {
        this.categoria = categoria;
        this.prezzoOrario = prezzoOrario;
        this.prezzoPerKm = prezzoPerKm;
    }
}
