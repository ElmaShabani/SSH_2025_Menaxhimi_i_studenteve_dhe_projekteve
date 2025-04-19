package com.example.student.service;

import com.example.student.domain.Student;
import com.example.student.domain.User;
import com.example.student.repo.StudentRepo;
import com.example.student.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Service
@RequestMapping("/users")
public class UserService {

    private final UserRepo userRepository;
    @Autowired
    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }
    @GetMapping
    public Page<User> getAllUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }


    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public User updateUser(String id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setFullname(updatedUser.getFullname());
            user.setAdmin(updatedUser.getAdmin());
            user.setEmail(updatedUser.getEmail());
            return userRepository.save(user);
        }).orElse(null);
    }

    public List<User> filterUser(String id, String email) {
        if (id != null && email != null) {
            return userRepository.findByIdAndEmail(id, email);
        } else if (id != null) {
            return userRepository.findById(id).map(List::of).orElse(List.of());
        } else if (email != null) {
            return userRepository.findByEmail(email).orElse(List.of());
        } else {
            return userRepository.findAll();
        }
    }

}
