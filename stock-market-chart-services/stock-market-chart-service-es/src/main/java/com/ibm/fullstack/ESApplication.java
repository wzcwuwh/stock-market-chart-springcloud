package com.ibm.fullstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ESApplication {

    public static void main(String[] args) {
        SpringApplication.run(ESApplication.class, args);
    }
}
