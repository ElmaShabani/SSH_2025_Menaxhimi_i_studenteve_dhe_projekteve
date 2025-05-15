package com.example.student.repo;
import com.example.student.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ProfessorRepo extends JpaRepository<Professor, UUID> {
}
