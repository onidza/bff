package com.onidza.bff;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

@RestControllerAdvice
public class RestClientExceptionHandler {

    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<String> handleRestClientResponseException(RestClientResponseException ex) {
        HttpHeaders headers = new HttpHeaders();

        if (ex.getResponseHeaders() != null && ex.getResponseHeaders().getContentType() != null) {
            headers.setContentType(ex.getResponseHeaders().getContentType());
        }

        return ResponseEntity
                .status(ex.getStatusCode())
                .headers(headers)
                .body(ex.getResponseBodyAsString());
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> handleResourceAccessException(ResourceAccessException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body("Backend service is unavailable");
    }
}
