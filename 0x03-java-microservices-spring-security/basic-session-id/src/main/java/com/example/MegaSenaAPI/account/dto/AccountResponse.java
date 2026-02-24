package com.example.MegaSenaAPI.account.dto;

import jakarta.validation.constraints.NotBlank;

public record AccountResponse(Long id, @NotBlank(message = "Invalid name: name is empty") String name) {}