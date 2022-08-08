package com.logobootcamp.adminpurchaseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdminPurchaseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminPurchaseServiceApplication.class, args);
	}

}
