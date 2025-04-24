package com.example.student.repo;

import com.example.student.domain.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectsRepo extends JpaRepository<Projects, String> {
    Optional<Projects> findById(String id);
}


