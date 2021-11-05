package com.lopesgon.redispoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "configuration")
public class RedisPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisPocApplication.class, args);
	}
}
