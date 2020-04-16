package com.nfinity.demo.petsdemos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PetsdemosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetsdemosApplication.class, args);
	}

}

