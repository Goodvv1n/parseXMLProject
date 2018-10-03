package com.pleshkov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ParseXmlProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParseXmlProjectApplication.class, args);
	}
}
