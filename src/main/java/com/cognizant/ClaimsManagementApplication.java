package com.cognizant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient(autoRegister = true)
public class ClaimsManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClaimsManagementApplication.class, args);
    }
}