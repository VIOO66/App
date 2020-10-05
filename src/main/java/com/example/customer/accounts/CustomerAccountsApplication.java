package com.example.customer.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CustomerAccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerAccountsApplication.class, args);
    }

}
