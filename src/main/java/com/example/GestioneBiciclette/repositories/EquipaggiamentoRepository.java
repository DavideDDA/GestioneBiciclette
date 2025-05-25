package com.example.GestioneBiciclette.repositories;


import com.example.GestioneBiciclette.models.Equipaggiamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipaggiamentoRepository extends JpaRepository<Equipaggiamento, Long> {
}
