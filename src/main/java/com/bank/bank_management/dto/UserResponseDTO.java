package com.bank.bank_management.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,
        BigDecimal balance
) {}