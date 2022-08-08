package com.logobootcamp.adminpaymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdminPaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminPaymentServiceApplication.class, args);
    }

}
