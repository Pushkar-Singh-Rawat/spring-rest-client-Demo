package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class RestConsumerDemo extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	
		return application.sources(RestConsumerDemo.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(RestConsumerDemo.class, args);
	}
}