package com.example.MegaSenaAPI.account.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateRequest(
    @NotBlank(message = "Invalid name: name is empty") String name,
    @NotBlank(message = "Password is required") String password
) {}