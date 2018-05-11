package com.example.wordcup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyEndpointsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyEndpointsApplication.class, args);
		System.out.print("Funciona!");
	}
}
