package com.example.GestioneBiciclette.security.repository;



import com.example.GestioneBiciclette.security.entity.ERole;
import com.example.GestioneBiciclette.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Optional<Role> findByRoleName(ERole roleName);

}
