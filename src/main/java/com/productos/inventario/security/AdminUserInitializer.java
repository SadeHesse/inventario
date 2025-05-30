package com.productos.inventario.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.productos.inventario.model.dao.User;
import com.productos.inventario.repository.UserDetailsRepository;

@Component
public class AdminUserInitializer {

     private static final Logger log = LoggerFactory.getLogger(AdminUserInitializer.class);

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

     @Bean
	public CommandLineRunner createAdminUser(){
		//log.info("Entre y llame ala funcion createAdminUser");

		return args -> {
			if (userDetailsRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ROLE_ADMIN");
                userDetailsRepository.save(admin);

            }
		};
	}
    
}
