package com.example.student.service;

import com.example.student.domain.Assignment;
import com.example.student.domain.Subject;
import com.example.student.dto.AssignmentDto;
import com.example.student.repo.AssignmentRepository;
import com.example.student.repo.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository, SubjectRepository subjectRepository) {
        this.assignmentRepository = assignmentRepository;
        this.subjectRepository = subjectRepository;
    }

    private final SubjectRepository subjectRepository;

    public AssignmentDto addAssignment(AssignmentDto dto) {
        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Assignment assignment = new Assignment();
        assignment.setTitle(dto.getTitle());
        assignment.setDescription(dto.getDescription());
        assignment.setDueDate(dto.getDueDate());
        assignment.setSubject(subject);

        assignment = assignmentRepository.save(assignment);

        dto.setId(assignment.getId());
        return dto;
    }

    public List<AssignmentDto> getAllAssignments() {
        return assignmentRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private AssignmentDto mapToDto(Assignment assignment) {
        AssignmentDto dto = new AssignmentDto();
        dto.setId(assignment.getId());
        dto.setTitle(assignment.getTitle());
        dto.setDescription(assignment.getDescription());
        dto.setDueDate(assignment.getDueDate());
        dto.setSubjectId(assignment.getSubject().getId());
        return dto;
    }
}
