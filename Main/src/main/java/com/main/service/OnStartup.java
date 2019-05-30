package com.main.service;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.main.security.model.Role;
import com.main.security.model.RoleName;
import com.main.security.model.User;
import com.main.security.repository.RoleRepository;
import com.main.security.repository.UserRepository;

@Component
public class OnStartup {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@PostConstruct
	private void onStartup() {

		if (!userRepository.existsByUsername("admin")) {

			String encodedPassword = encoder.encode("iskratel");
			User user = new User("admin", "admin", "admin@iskratel.com", encodedPassword);

			Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
			roleRepository.save(roleAdmin);

			Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			user.setRoles(roles);
			userRepository.save(user);

		} else {
			
			System.out.println();
			System.out.println("Such a user already exists");
			System.out.println();
			
		}
	}
}
