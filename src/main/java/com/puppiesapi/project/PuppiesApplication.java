package com.puppiesapi.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class PuppiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PuppiesApplication.class, args);
	}

}
