package com.example.student.repo;

import com.example.student.domain.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    // Mund të shtosh kërkesa të personalizuara këtu
}




