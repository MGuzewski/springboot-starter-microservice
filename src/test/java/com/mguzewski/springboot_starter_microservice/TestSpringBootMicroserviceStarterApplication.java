package com.mguzewski.springboot_starter_microservice;

import org.springframework.boot.SpringApplication;

public class TestSpringBootMicroserviceStarterApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringBootMicroserviceStarterApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
