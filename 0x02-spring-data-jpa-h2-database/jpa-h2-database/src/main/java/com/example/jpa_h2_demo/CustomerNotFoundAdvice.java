package com.example.jpa_h2_demo;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerNotFoundAdvice {
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String songNotFoundHandler(CustomerNotFoundException customerNotFoundException) {
        return customerNotFoundException.getMessage();
    }
}