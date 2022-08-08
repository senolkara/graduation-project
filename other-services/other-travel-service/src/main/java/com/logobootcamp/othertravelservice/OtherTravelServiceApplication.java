package com.logobootcamp.othertravelservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OtherTravelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtherTravelServiceApplication.class, args);
	}

}
