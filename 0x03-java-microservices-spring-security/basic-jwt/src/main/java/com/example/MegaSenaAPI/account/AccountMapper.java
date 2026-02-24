package com.example.MegaSenaAPI.account;

import com.example.MegaSenaAPI.account.dto.AccountResponse;
import com.example.MegaSenaAPI.account.dto.RegisterRequest;

import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public RegisterRequest toRegisterRequest(Account account) {
        return new RegisterRequest(account.getName(), account.getPassword());
    }

    public Account toAccount(RegisterRequest registerRequest) {
        return new Account(null, registerRequest.name(),  registerRequest.password());
    }

    public AccountResponse toAccountResponse(Account account) {
        return new AccountResponse(account.getId(), account.getName());
    }
}