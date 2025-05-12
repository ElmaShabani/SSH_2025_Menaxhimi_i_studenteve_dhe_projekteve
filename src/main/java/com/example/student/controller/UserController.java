package com.example.student.controller;

import com.example.student.domain.Role;
import com.example.student.domain.User;
import com.example.student.dto.PasswordChangeDto;
import com.example.student.dto.UserDto;
import com.example.student.service.UserService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@Data
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/{id}/change-password")
    public ResponseEntity<?> changePassword(@PathVariable String id,
                                            @RequestBody PasswordChangeDto passwordChangeDto,
                                            Principal principal) {
        User currentUser = userService.findByEmail(principal.getName()).orElse(null);
        if (currentUser == null || (!currentUser.getId().equals(id) && currentUser.getRole() != Role.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not allowed to change this password.");
        }

        boolean changed = userService.changePassword(id, passwordChangeDto);
        if (changed) {
            return ResponseEntity.ok("Password changed successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Old password is incorrect.");
        }
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}


