package com.udacity.jwdnd.c1.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@SpringBootApplication
@Configuration
public class ReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewApplication.class, args);
	}


//	@Bean
//	public String message() {
//		System.out.println("Hello, String");
//		return "Hello Message";
//	}
//
//	@Bean
//	public String uppercaseMessage(MessageService ms){
//		String newStr = ms.uppercase();
//		System.out.println("uppercaseMessage: " + newStr);
//		return newStr;
//	}
//
//	@Bean
//	public String lowecaseMessage(MessageService ms){
//		String newStr = ms.lowercase();
//		System.out.println("lowercaseMessage: " + newStr);
//		return newStr;
//	}




}
