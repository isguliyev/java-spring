package com.example.MegaSenaAPI.account;

import com.example.MegaSenaAPI.account.dto.AccountResponse;
import com.example.MegaSenaAPI.account.dto.RegisterRequest;
import com.example.MegaSenaAPI.account.dto.UpdateRequest;

import com.example.MegaSenaAPI.account.exception.AccountDoesNotExistException;
import com.example.MegaSenaAPI.account.exception.AccountNameAlreadyTakenException;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public AccountResponse registerAccount(RegisterRequest registerRequest) {
        if (this.accountRepository.existsByName(registerRequest.name())) {
            throw new AccountNameAlreadyTakenException(registerRequest.name());
        }

        return this.accountMapper.toAccountResponse(
            this.accountRepository.save(
                this.accountMapper.toAccount(registerRequest)
            )
        );
    }

    public AccountResponse updateAccountById(UpdateRequest updateRequest, Long id) {
        if (this.accountRepository.existsByName(updateRequest.name())) {
            throw new AccountNameAlreadyTakenException(updateRequest.name());
        }

        Account accountToUpdate = this.accountRepository.findById(id).orElseThrow(
            () -> new AccountDoesNotExistException(updateRequest.name())
        );

        accountToUpdate.setName(updateRequest.name());
        accountToUpdate.setPassword(updateRequest.password());

        return this.accountMapper.toAccountResponse(this.accountRepository.save(accountToUpdate));
    }

    public Collection<AccountResponse> findAllAccounts() {
        return this.accountRepository
            .findAll()
            .stream()
            .map(this.accountMapper::toAccountResponse)
            .collect(Collectors.toSet());
    }

    public AccountResponse findAccountById(Long id) {
        return this.accountMapper.toAccountResponse(
            this.accountRepository.findById(id).orElseThrow(
                () -> new AccountDoesNotExistException(id)
            )
        );
    }

    public void deleteAccountById(Long id) {
        if (!this.accountRepository.existsById(id)) {
            throw new AccountDoesNotExistException(id);
        }

        this.accountRepository.deleteById(id);
    }
}