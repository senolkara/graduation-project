package com.logobootcamp.otherpaymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OtherPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtherPaymentServiceApplication.class, args);
	}

}
