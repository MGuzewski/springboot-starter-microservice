package com.mguzewski.springboot_starter_microservice.api;

import com.mguzewski.springboot_starter_microservice.exception.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, String>> handleApiException(final ApiException e, final HttpServletRequest request) {
        log.warn("API exception: {} {} -> {}: {}", request.getMethod(), request.getRequestURI(), e.getHttpStatus(), e.getMessage());
        final var body = prepareErrorResponse(e.getMessage(), e.getHttpStatus(), e.getTimestamp(), request.getRequestURI());
        return status(e.getHttpStatus()).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleUnexpected(final Exception e, final HttpServletRequest request) {
        log.error("Unexpected exception: {} {}", request.getMethod(), request.getRequestURI(), e);
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        final var body = prepareErrorResponse(e.getMessage(), status, Instant.now(), request.getRequestURI());
        return status(status).body(body);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Void> handleNoResource() {
        return ResponseEntity.notFound().build();
    }

    private static Map<String, String> prepareErrorResponse(final String message, final HttpStatus status, final Instant timestamp, final String uri) {
        final Map<String, String> result = new HashMap<>();
        result.put("status", String.valueOf(status));
        result.put("message", message);
        result.put("uri", uri);
        result.put("timestamp", String.valueOf(timestamp));
        return result;
    }
}
