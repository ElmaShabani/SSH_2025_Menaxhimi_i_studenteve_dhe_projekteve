package com.example.student.repo;

import com.example.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, String>{


    Optional<Student> findByEmail(String email);

    Optional<Student> findById(String id);

    List<Student> findByIdAndEmail(String id, String email);

}
