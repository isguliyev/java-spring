package com.example.MegaSenaAPI.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
    @NotNull(message = "Invalid name: name is null") @NotBlank(message = "Invalid name: name is empty") String name,
    @NotNull(message = "Invalid password: password is null") @NotBlank(message = "Invalid password: password is empty") String password
) {}