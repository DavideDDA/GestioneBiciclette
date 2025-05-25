package com.example.GestioneBiciclette.repositories;


import com.example.GestioneBiciclette.models.Parcheggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcheggioRepository extends JpaRepository<Parcheggio, Long> {
}
