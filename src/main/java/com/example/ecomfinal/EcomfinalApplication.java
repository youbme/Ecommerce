package com.example.ecomfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EntityScan(basePackages = {"com.example.ecomfinal.model"})
//@EnableJpaRepositories(basePackages = "com.example.ecomfinal.repository")
public class EcomfinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcomfinalApplication.class, args);
    }

}
