package com.example.student.controller;

import com.example.student.domain.User;
import com.example.student.dto.UserDto;
import com.example.student.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
//@RequiredArgsConstructor
public class AuthController {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthController(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo= userRepo;
        this.passwordEncoder=passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserDto dto) {
        if (userRepo.findByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setFullname(dto.getFullname());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setAdmin(false);

        return ResponseEntity.ok(userRepo.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto dto) {
        return userRepo.findByEmail(dto.getEmail())
                .filter(user -> passwordEncoder.matches(dto.getPassword(), user.getPasswordHash()))
                .map(user -> ResponseEntity.ok("Login successful!"))
                .orElse(ResponseEntity.status(401).body("Invalid credentials"));
    }
}