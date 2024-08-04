package com.example.spring_boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TLabApplication implements CommandLineRunner {

	@Autowired
	private TranslationService translationService;

	public static void main(String[] args) {
		SpringApplication.run(TLabApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println();
	}
}