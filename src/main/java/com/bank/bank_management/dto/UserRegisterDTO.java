package com.bank.bank_management.dto;

public record UserRegisterDTO(
        String name,
        String email,
        String password
) {}