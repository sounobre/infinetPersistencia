package com.infnet.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class CanarilApplication {

	public static void main(String[] args) {
		SpringApplication.run(CanarilApplication.class, args);
	}

}
