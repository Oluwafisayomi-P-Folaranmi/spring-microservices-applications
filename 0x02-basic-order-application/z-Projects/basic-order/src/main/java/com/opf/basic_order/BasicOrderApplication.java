package com.opf.basic_order;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BasicOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicOrderApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return runner -> {
			System.out.println("Welcoming the developer. The application is running.");
		};
	}

}
