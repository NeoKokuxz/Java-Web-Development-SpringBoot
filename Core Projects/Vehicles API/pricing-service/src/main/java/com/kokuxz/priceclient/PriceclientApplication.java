package com.kokuxz.priceclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PriceclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(PriceclientApplication.class, args);
    }

}
