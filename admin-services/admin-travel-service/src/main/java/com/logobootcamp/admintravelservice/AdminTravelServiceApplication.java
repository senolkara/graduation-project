package com.logobootcamp.admintravelservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdminTravelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminTravelServiceApplication.class, args);
	}

}
