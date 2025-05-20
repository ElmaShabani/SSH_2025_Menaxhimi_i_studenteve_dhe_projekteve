package com.example.student.repo;

import com.example.student.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ResourceRepo extends JpaRepository<Resource, Long> {
    List<Resource> findBySubjectId(String subjectId);
    List<Resource> findByProfessorId(UUID professorId);

}


