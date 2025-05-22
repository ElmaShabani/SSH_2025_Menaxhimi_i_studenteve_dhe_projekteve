package com.example.student.service;

import com.example.student.domain.Role;
import com.example.student.domain.User;
import com.example.student.dto.PasswordChangeDto;
import com.example.student.dto.UserDto;
import com.example.student.repo.UserRepo;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(UserDto userDTO) {
        User user = new User();
        String userId = UUID.randomUUID().toString();
        user.setId(userId);
        user.setFullname(userDTO.getFullname());
        user.setEmail(userDTO.getEmail());

        Role role = userDTO.getRole() != null ? userDTO.getRole() : Role.STUDENT;
        user.setRole(role);

        if (role == Role.ADMIN) {
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

}