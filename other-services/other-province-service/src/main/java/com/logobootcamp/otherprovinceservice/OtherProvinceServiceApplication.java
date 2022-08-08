package com.logobootcamp.otherprovinceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OtherProvinceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtherProvinceServiceApplication.class, args);
	}

}
