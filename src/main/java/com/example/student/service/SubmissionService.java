package com.example.student.service;

import com.example.student.domain.Assignment;
import com.example.student.domain.Student;
import com.example.student.domain.Submission;
import com.example.student.domain.SubmissionStatus;
import com.example.student.dto.SubmissionCreateDTO;
import com.example.student.dto.SubmissionResponseDTO;
import com.example.student.repo.AssignmentRepository;
import com.example.student.repo.StudentRepo;
import com.example.student.repo.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    public SubmissionResponseDTO createSubmission(SubmissionCreateDTO dto) {
        System.out.println("Krijimi i submission me AssignmentId: " + dto.getAssignmentId());
        System.out.println("StudentId: " + dto.getStudentId());

        // Merr studentin
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student nuk u gjet me ID: " + dto.getStudentId()));

        // Merr assignmentin
        Assignment assignment = assignmentRepository.findById(dto.getAssignmentId())
                .orElseThrow(() -> new RuntimeException("Assignment nuk u gjet me ID: " + dto.getAssignmentId()));

        // Krijo submission të ri
        Submission submission = new Submission();
        submission.setStudent(student);
        submission.setAssignment(assignment);
        submission.setFileUrl(dto.getFileUrl());
        submission.setSubmittedAt(dto.getSubmittedAt() != null ? dto.getSubmittedAt() : LocalDateTime.now());
        submission.setComment(dto.getComment());
        submission.setStatus(SubmissionStatus.SUBMITTED);

        // Ruaj submission-in
        Submission saved = submissionRepository.save(submission);

        // Kthe DTO
        return mapToDTO(saved);
    }

    private SubmissionResponseDTO mapToDTO(Submission submission) {
        if (submission.getAssignment() == null) {
            throw new RuntimeException("Submission nuk ka assignment të lidhur.");
        }
        if (submission.getStudent() == null) {
            throw new RuntimeException("Submission nuk ka student të lidhur.");
        }

        SubmissionResponseDTO dto = new SubmissionResponseDTO();
        dto.setId(submission.getId());
        dto.setStudentId(submission.getStudent().getId());
        dto.setAssignmentId(submission.getAssignment().getId());
        dto.setFileUrl(submission.getFileUrl());
        dto.setSubmittedAt(submission.getSubmittedAt());
        dto.setComment(submission.getComment());
        dto.setStatus(submission.getStatus().name());

        return dto;
    }
}
