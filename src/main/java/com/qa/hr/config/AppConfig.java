package com.qa.hr.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qa.hr.entities.Email;

@Configuration
public class AppConfig {
	
	@Bean
	public LocalDate timeNow() {
		return LocalDate.now();
	}
	
	@Bean
	public Email birthdayEmail() {
		return new Email(0, "It's your birthday!", "According to all known laws of aviation, "
				+ "there is no way a bee should be able to fly. Its wings are too small to "
				+ "get its fat little body off the ground. The bee, of course, flies anyway "
				+ "because bees don't care what humans think is impossible.");
	}
}
