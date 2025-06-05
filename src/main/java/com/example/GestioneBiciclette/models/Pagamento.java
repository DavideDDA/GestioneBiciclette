package com.example.GestioneBiciclette.models;

import com.example.GestioneBiciclette.models.enumerated.TipoPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name ="pagamenti")
public class Pagamento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double importo;

    private LocalDateTime dataPagamento = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pagamento", nullable = false)
    private TipoPagamento metodo;

    @OneToOne
    @JoinColumn(name = "prenotazione_id", nullable = false)
    private Prenotazione prenotazione;

}
