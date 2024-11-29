package com.desafio.santander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DesafioSantanderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioSantanderApplication.class, args);
	}

}
