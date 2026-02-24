package com.example.jpa_h2_demo;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super(String.format("Customer not found. No customer exists with id: %d", id));
    }
}