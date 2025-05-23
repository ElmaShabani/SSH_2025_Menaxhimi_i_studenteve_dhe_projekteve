package com.example.student;

import com.example.student.domain.*;
import com.example.student.dto.ResourceRequestDto;
import com.example.student.dto.ResourceResponseDTO;
import com.example.student.repo.*;
import com.example.student.service.ResourceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ResourceServiceTest {

    @Mock private ResourceRepo resourceRepo;
    @Mock private SubjectRepository subjectRepo;
    @Mock private ProfessorRepo professorRepo;
    @Mock private StudentSemesterRepository studentSemesterRepo;
    @Mock private FileUploadRepository fileUploadRepo;

    @InjectMocks private ResourceService resourceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateMaterial_Success() {
        // Arrange
        ResourceRequestDto dto = new ResourceRequestDto();
        dto.setTitle("Lecture Material");
        dto.setDescription("Advanced Java Concepts");
        dto.setSubjectId(String.valueOf(1L));
        dto.setProfessorId(UUID.randomUUID().toString());
        dto.setSemesterId("2");
        dto.setFileUploadId("3");

        Subject subject = new Subject();
        subject.setId(String.valueOf(1L));
        subject.setName("Java Programming");

        Professor professor = new Professor();
        professor.setId(UUID.fromString(dto.getProfessorId()));
        professor.setName("Dr. Smith");

        Semester semester = new Semester();
        semester.setId(2L);
        semester.setName("Spring 2025");

        StudentSemester studentSemester = new StudentSemester();
        studentSemester.setSemester(semester);

        FileUpload fileUpload = new FileUpload();
        fileUpload.setId(3L);
        fileUpload.setFileUrl("http://fileserver.com/java_lecture.pdf");

        Resource savedResource = new Resource();
        savedResource.setId(10L);
        savedResource.setTitle(dto.getTitle());
        savedResource.setDescription(dto.getDescription());
        savedResource.setSubject(subject);
        savedResource.setProfessor(professor);
        savedResource.setSemester(semester);
        savedResource.setFileUpload(fileUpload);
        savedResource.setUploadDate(LocalDateTime.now());

        when(subjectRepo.findById(String.valueOf(1L))).thenReturn(Optional.of(subject));
        when(professorRepo.findById(UUID.fromString(dto.getProfessorId()))).thenReturn(Optional.of(professor));
        when(studentSemesterRepo.findById(Long.parseLong(dto.getSemesterId()))).thenReturn(Optional.of(studentSemester));
        when(fileUploadRepo.findById(Long.parseLong(dto.getFileUploadId()))).thenReturn(Optional.of(fileUpload));
        when(resourceRepo.save(any(Resource.class))).thenReturn(savedResource);

        // Act
        ResourceResponseDTO response = resourceService.createMaterial(dto);

        // Assert
        assertNotNull(response);
        assertEquals("Lecture Material", response.getTitle());
        assertEquals("Dr. Smith", response.getProfessorName());
        assertEquals("Java Programming", response.getSubjectName());
        assertEquals("Spring 2025", response.getSemesterName());
        assertEquals("http://fileserver.com/java_lecture.pdf", response.getFileUrl());
    }
}
