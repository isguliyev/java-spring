package com.example.MegaSenaAPI.configuration;

import com.example.MegaSenaAPI.account.AccountService;
import com.example.MegaSenaAPI.account.dto.RegisterRequest;

import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class LoadDataBase {
    @Bean
    public CommandLineRunner initDataBase(
        AccountService accountService,
        PasswordEncoder passwordEncoder
    ) {
        return args -> {
            accountService.registerAccount(
                new RegisterRequest("Naruto", passwordEncoder.encode("n@ruto"))
            );

            accountService.registerAccount(
                new RegisterRequest("Sasuke", passwordEncoder.encode("s@suke"))
            );
        };
    }
}