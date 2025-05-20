package com.example.student.repo;

import com.example.student.domain.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, String> {

    List<Submission> findByStudentId(String studentId);

    List<Submission> findByAssignmentId(String assignmentId);
}
