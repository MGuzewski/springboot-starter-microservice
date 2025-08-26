package com.mguzewski.springboot_starter_microservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AlreadyExistsApiException extends ApiException {

    public AlreadyExistsApiException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
