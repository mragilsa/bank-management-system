package com.bank.bank_management.service;

import com.bank.bank_management.dto.UserLoginDTO;
import com.bank.bank_management.dto.UserRegisterDTO;
import com.bank.bank_management.dto.UserResponseDTO;
import com.bank.bank_management.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDTO register(UserRegisterDTO dto);
    UserResponseDTO login(UserLoginDTO dto);
    UserResponseDTO getById(UUID id);
    BigDecimal checkBalance(String email, String password);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO adjustBalance(UUID id, String amount);
    void deleteUser(UUID id);
    UserResponseDTO updateProfile(String email, String password, String name);
}