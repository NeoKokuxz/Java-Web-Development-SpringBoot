package com.kokuxz.consumingdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumingRestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumingRestDemoApplication.class, args);
    }

    //Using RestTemplate will benefit from all the auto configuration that in Spring Boot
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Bean
    public CommandLineRunner run (RestTemplate restTemplate) throws Exception {
        return args -> {
            //Entity/Model class that matches the json return

            System.out.println();
        }
    }

}
