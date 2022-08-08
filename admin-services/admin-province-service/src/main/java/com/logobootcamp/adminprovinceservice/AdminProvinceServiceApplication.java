package com.logobootcamp.adminprovinceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdminProvinceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminProvinceServiceApplication.class, args);
	}

}
