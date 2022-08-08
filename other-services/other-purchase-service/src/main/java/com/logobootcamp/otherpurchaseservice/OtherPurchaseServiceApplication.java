package com.logobootcamp.otherpurchaseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OtherPurchaseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtherPurchaseServiceApplication.class, args);
	}

}
