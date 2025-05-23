package com.example.student.service;

import com.example.student.domain.Role;
import com.example.student.domain.User;
import com.example.student.dto.PasswordChangeDto;
import com.example.student.dto.UserDto;
import com.example.student.repo.RoleRepo;
import com.example.student.repo.UserRepo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
@Getter
@Setter
@RequestMapping("/users")
public class UserService {
    private final UserRepo userRepository;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepository, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(UserDto userDTO) {
        User user = new User();
        String userId = UUID.randomUUID().toString();
        user.setId(userId);
        user.setFullname(userDTO.getFullname());
        user.setEmail(userDTO.getEmail());

        String roleName = userDTO.getRole().getName();
        Role role = roleRepo.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Roli " + roleName + " nuk ekziston në databazë"));

        user.setRole(role);

        if ("ADMIN".equalsIgnoreCase(role.getName())) {
            user.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
        } else {
            user.setPasswordHash(passwordEncoder.encode(userId));
        }

        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean changePassword(String userId, PasswordChangeDto dto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(dto.getOldPassword(), user.getPasswordHash())) {
                user.setPasswordHash(passwordEncoder.encode(dto.getNewPassword()));
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public boolean hasPermission(String email, String verb, String resource) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) return false;

        User user = userOpt.get();
        Role role = user.getRole();
        if (role == null || role.getPermissions() == null) return false;

        return role.getPermissions().stream()
                .anyMatch(p -> p.getVerb().equalsIgnoreCase(verb)
                        && p.getResource().equalsIgnoreCase(resource));
    }
}
