package com.YvesJadTarek.EAI_AST;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EaiAstApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EaiAstApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Working");
	}
}
