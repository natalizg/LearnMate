package com.learnmate.learnmateback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class LearnMateApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnMateApplication.class, args);
	}

}
