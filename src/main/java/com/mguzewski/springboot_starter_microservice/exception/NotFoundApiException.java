package com.mguzewski.springboot_starter_microservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundApiException extends ApiException {

    public NotFoundApiException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
