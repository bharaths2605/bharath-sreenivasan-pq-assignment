package com.technical.payconiqchallenge;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.technical.payconiqchallenge.entity")
@EnableJpaRepositories("com.technical.payconiqchallenge.repository")
@ComponentScan(basePackages = "com.technical.payconiqchallenge")
public class PayconiqChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayconiqChallengeApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
