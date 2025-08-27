package com.mguzewski.springboot_starter_microservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI appOpenAPI() {
        return new OpenAPI().info(new Info()
                        .title("Spring Boot Starter Microservice")
                        .description("Basic CRUD API for Users")
                        .version("v1.0.0"));
    }
}
