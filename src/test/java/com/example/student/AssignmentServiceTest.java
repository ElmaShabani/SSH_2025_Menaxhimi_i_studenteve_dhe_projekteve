package com.example.student;

import com.example.student.domain.Assignment;
import com.example.student.domain.Subject;
import com.example.student.dto.AssignmentDto;
import com.example.student.repo.AssignmentRepository;
import com.example.student.repo.SubjectRepository;
import com.example.student.service.AssignmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssignmentServiceTest {

    @Mock
    private AssignmentRepository assignmentRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private AssignmentService assignmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAssignment_success() {
        // Arrange
        AssignmentDto dto = new AssignmentDto();
        dto.setTitle("Projekt Java");
        dto.setDescription("Përshkrimi i projektit");
        dto.setDueDate(LocalDate.of(2025, 6, 15));
        dto.setSubjectId(String.valueOf(1L));

        Subject subject = new Subject();
        subject.setId(String.valueOf(1L));

        Assignment savedAssignment = new Assignment();
        savedAssignment.setId(UUID.randomUUID().toString());
        savedAssignment.setTitle(dto.getTitle());
        savedAssignment.setDescription(dto.getDescription());
        savedAssignment.setDueDate(dto.getDueDate());
        savedAssignment.setSubject(subject);

        when(subjectRepository.findById(String.valueOf(1L))).thenReturn(Optional.of(subject));
        when(assignmentRepository.save(any(Assignment.class))).thenReturn(savedAssignment);

        // Act
        AssignmentDto result = assignmentService.addAssignment(dto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(dto.getTitle(), result.getTitle());
    }

    @Test
    void testGetAllAssignments() {
        // Arrange
        Subject subject = new Subject();
        subject.setId(String.valueOf(1L));

        Assignment assignment = new Assignment();
        assignment.setId("a1");
        assignment.setTitle("Projekt OOP");
        assignment.setDescription("Detyrë për Java");
        assignment.setDueDate(LocalDate.of(2025, 6, 10));
        assignment.setSubject(subject);

        when(assignmentRepository.findAll()).thenReturn(List.of(assignment));

        // Act
        List<AssignmentDto> results = assignmentService.getAllAssignments();

        // Assert
        assertEquals(1, results.size());
        assertEquals("Projekt OOP", results.get(0).getTitle());
        assertEquals(1L, results.get(0).getSubjectId());
    }
}
