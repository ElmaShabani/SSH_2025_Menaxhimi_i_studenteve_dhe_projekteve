package com.example.student.repo;

import com.example.student.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {

    List<User> findByIdAndEmail(String id, String email);

    Optional<User> findById(String id);

    Optional<List<User>> findByEmail(String email);
}
