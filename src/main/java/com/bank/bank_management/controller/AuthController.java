package com.bank.bank_management.controller;

import com.bank.bank_management.dto.UserLoginDTO;
import com.bank.bank_management.dto.UserRegisterDTO;
import com.bank.bank_management.dto.UserResponseDTO;
import com.bank.bank_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody UserRegisterDTO dto) {
        return userService.register(dto);
    }

    @PostMapping("/login")
    public UserResponseDTO login(@RequestBody UserLoginDTO dto) {
        return userService.login(dto);
    }
}