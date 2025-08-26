package com.mguzewski.springboot_starter_microservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestApiException extends ApiException {

    public BadRequestApiException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
