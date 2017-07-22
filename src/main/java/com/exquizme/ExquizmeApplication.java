package com.exquizme;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ExquizmeApplication {
	public static void main(String[] args) {
		String property = System.getProperty("spring.profiles.active");
		log.debug("spring.profiles.active: {}", property);

		SpringApplication.run(ExquizmeApplication.class, args);
	}
}