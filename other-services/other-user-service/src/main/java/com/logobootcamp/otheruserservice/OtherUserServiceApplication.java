package com.logobootcamp.otheruserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OtherUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtherUserServiceApplication.class, args);
	}

}
