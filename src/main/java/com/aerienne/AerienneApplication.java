package com.aerienne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.aerienne", "controller", "dto", "entity", "repository", "service"})
@EnableJpaRepositories(basePackages = {"repository"})
@EntityScan(basePackages = {"entity"})
public class AerienneApplication {

    public static void main(String[] args) {
        SpringApplication.run(AerienneApplication.class, args);
    }
}
