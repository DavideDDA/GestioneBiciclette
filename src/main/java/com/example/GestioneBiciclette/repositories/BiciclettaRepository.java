package com.example.GestioneBiciclette.repositories;


import com.example.GestioneBiciclette.models.Bicicletta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiciclettaRepository extends JpaRepository<Bicicletta, Long> {
}
