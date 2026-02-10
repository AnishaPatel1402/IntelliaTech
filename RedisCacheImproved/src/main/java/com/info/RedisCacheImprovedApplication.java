package com.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.info.repository")
public class RedisCacheImprovedApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisCacheImprovedApplication.class, args);
	}

}
