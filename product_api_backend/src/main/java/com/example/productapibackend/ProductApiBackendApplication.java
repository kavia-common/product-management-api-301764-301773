package com.example.productapibackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot application entry point.
 * Scans application components including controllers and configuration.
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.example.productapibackend",
        "com.example.demo"
})
public class ProductApiBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApiBackendApplication.class, args);
    }
}
