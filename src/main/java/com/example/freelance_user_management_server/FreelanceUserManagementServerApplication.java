package com.example.freelance_user_management_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity(securedEnabled = true)
public class FreelanceUserManagementServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreelanceUserManagementServerApplication.class, args);
	}

}
