package com.WebApp.WeatherApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@CrossOrigin(origins = "http://localhost:4200")
public class WeatherAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(WeatherAppApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
