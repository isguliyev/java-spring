package com.example.MegaSenaAPI.account.exception;

public class AccountDoesNotExistException extends RuntimeException {
    public AccountDoesNotExistException(String name) {
        super(String.format("Account with name: '%s' was not found", name));
    }

    public AccountDoesNotExistException(Long id) {
        super(String.format("Account with id: '%d' was not found", id));
    }
}