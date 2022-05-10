package com.technical.paycoinzchallenge;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.technical.paycoinzchallenge.entity")
@EnableJpaRepositories("com.technical.paycoinzchallenge.repository")
@ComponentScan("com.technical.paycoinzchallenge.Iservice")
@ComponentScan("com.technical.paycoinzchallenge.controller")
public class PaycoinzchallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaycoinzchallengeApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}
