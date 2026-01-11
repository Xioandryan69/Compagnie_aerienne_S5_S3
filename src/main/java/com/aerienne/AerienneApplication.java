package com.aerienne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.aerienne", "controller", "dto", "entity", "repository", "service"})
public class AerienneApplication {

    public static void main(String[] args) {
        SpringApplication.run(AerienneApplication.class, args);
    }
}
