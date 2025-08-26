package com.mguzewski.springboot_starter_microservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
public class ApiException extends RuntimeException {
    private Instant timestamp;
    private HttpStatus httpStatus;

    protected ApiException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.timestamp = Instant.now();
        this.httpStatus = httpStatus;
    }

    private ApiException() {

    }
}
