package com.example.MegaSenaAPI.account.exception;

public class AccountNameAlreadyTakenException extends RuntimeException {
    public AccountNameAlreadyTakenException(String name) {
        super(String.format("Account name '%s' is already taken", name));
    }
}