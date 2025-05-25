package com.example.GestioneBiciclette.security.runner;

import com.example.GestioneBiciclette.security.entity.ERole;
import com.example.GestioneBiciclette.security.entity.Role;
import com.example.GestioneBiciclette.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthRunner implements ApplicationRunner {
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Run...");

		// Leggo nel DB se sono già presenti ruoli salvati
		List<Role> roleList = roleRepository.findAll();
		if(roleList.size() == 0) {
			// Metodo da lanciare solo la prima volta
			// Serve per inizializzare i ruoli nel DB
			setRoleDefault();
		} else {
			System.out.println(roleList);
		}
		
	}
	
	private void setRoleDefault() {
		// Creo un ruolo Admin e lo salvo nel DB
		Role admin = new Role();
		admin.setRoleName(ERole.ROLE_ADMIN);
		roleRepository.save(admin);

		// Creo un ruolo User e lo salvo nel DB
		Role client = new Role();
		client.setRoleName(ERole.ROLE_CLIENT);
		roleRepository.save(client);

/*		// Creo un ruolo Moderator e lo salvo nel DB
		Role instructor = new Role();
		instructor.setRoleName(ERole.ROLE_INSTRUCTOR);
		roleRepository.save(instructor);*/

	}
	
	

}
