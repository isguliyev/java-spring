package com.example.MegaSenaAPI.account;

import jakarta.validation.constraints.NotNull;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class AccountDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public AccountDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public @NotNull UserDetails loadUserByUsername(
        @NotNull String username
    ) throws UsernameNotFoundException {
        return new AccountPrincipals(
            this.accountRepository.findByName(username).orElseThrow(
                () -> UsernameNotFoundException.fromUsername(username)
            )
        );
    }
}