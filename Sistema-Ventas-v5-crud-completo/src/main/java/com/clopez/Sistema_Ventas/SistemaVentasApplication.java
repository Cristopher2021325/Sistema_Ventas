package com.clopez.Sistema_Ventas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class  SistemaVentasApplication implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
	System.out.println("Test Api ...");
	}

	public static void main(String[] args) {
		SpringApplication.run(SistemaVentasApplication.class, args);
	}

}
