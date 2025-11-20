package com.bank.bank_management.controller;

import com.bank.bank_management.dto.UserResponseDTO;
import com.bank.bank_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable UUID id) {
        return userService.getById(id);
    }

    @GetMapping("/me/balance")
    public BigDecimal checkBalance(@RequestParam String email,
                                   @RequestParam String password) {
        return userService.checkBalance(email, password);
    }

    @PutMapping("/me")
    public UserResponseDTO updateProfile(@RequestParam String email,
                                         @RequestParam String password,
                                         @RequestParam String name) {
        return userService.updateProfile(email, password, name);
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/admin/{id}/adjust-balance")
    public UserResponseDTO adjustBalance(@PathVariable UUID id,
                                         @RequestParam String amount) {
        return userService.adjustBalance(id, amount);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}