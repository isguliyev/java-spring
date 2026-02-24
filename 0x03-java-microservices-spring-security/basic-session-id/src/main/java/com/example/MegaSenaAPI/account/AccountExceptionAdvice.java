package com.example.MegaSenaAPI.account;

import com.example.MegaSenaAPI.account.exception.AccountDoesNotExistException;
import com.example.MegaSenaAPI.account.exception.AccountNameAlreadyTakenException;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountExceptionAdvice {
    @ExceptionHandler(AccountDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String accountDoesNotExistExceptionHandler(
        AccountDoesNotExistException accountDoesNotExistException
    ) {
        return accountDoesNotExistException.getMessage();
    }

    @ExceptionHandler(AccountNameAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String accountNameAlreadyTakenExceptionHandler(
        AccountNameAlreadyTakenException accountNameAlreadyTakenException
    ) {
        return accountNameAlreadyTakenException.getMessage();
    }
}