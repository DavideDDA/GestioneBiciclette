package com.example.GestioneBiciclette.repositories;

import com.example.GestioneBiciclette.models.CategoriaBicicletta;
import com.example.GestioneBiciclette.models.Tariffa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffaRepository extends JpaRepository<Tariffa, Long> {
    Tariffa findByCategoria(CategoriaBicicletta categoriaBicicletta);
}
