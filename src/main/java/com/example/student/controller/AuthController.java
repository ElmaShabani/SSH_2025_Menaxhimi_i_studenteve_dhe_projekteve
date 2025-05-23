package com.example.student.controller;

import com.example.student.domain.User;
import com.example.student.dto.UserDto;
import com.example.student.repo.UserRepo;
import com.example.student.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


//@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserDto dto) {
        if (userService.findByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        User savedUser = userService.createUser(dto);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto dto) {
        Optional<User> userOpt = userService.findByEmail(dto.getEmail());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (userService.getPasswordEncoder().matches(dto.getPassword(), user.getPasswordHash())) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Login successful!");
                response.put("role", user.getRole().getName());
                response.put("name", user.getFullname());
                response.put("id", user.getId());
                return ResponseEntity.ok(response); // ✅ tani kthen Map
            }
        }
        // error për login të pasaktë
        Map<String, String> error = new HashMap<>();
        error.put("error", "Invalid credentials");
        return ResponseEntity.status(401).body(error);
    }
}