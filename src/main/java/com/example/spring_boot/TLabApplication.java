package com.example.spring_boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TLabApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(TLabApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello");
	}
}