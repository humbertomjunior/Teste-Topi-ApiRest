package br.com.topi.teste;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TesteApplication {

	private static final Logger logger = LoggerFactory.getLogger(TesteApplication.class);
	
	public static void main(String[] args) {
		logger.info("Iniciando a aplicação");
		System.out.println("Iniciando a aplicação");
		SpringApplication.run(TesteApplication.class, args);
	}

}
