package com.example.guarderia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GuarderiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuarderiaApplication.class, args);
    }

}

