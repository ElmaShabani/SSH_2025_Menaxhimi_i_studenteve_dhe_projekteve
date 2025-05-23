package com.example.student.service;

import com.example.student.domain.Professor;
import com.example.student.domain.Role;
import com.example.student.domain.User;
import com.example.student.dto.ProfessorDto;
import com.example.student.repo.ProfessorRepo;
import com.example.student.repo.RoleRepo;
import com.example.student.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfessorServiceTest {

    @Mock
    private ProfessorRepo professorRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepo roleRepo;

    @InjectMocks
    private ProfessorService professorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProfessor() {
        // arrange
        ProfessorDto dto = new ProfessorDto();
        dto.setName("Dr. Arben");
        dto.setEmail("arben@example.com");
        dto.setTitle("Prof. Dr.");

        Professor savedProfessor = new Professor();
        UUID profId = UUID.randomUUID();
        savedProfessor.setId(profId);
        savedProfessor.setName(dto.getName());
        savedProfessor.setEmail(dto.getEmail());
        savedProfessor.setTitle(dto.getTitle());

        when(professorRepo.save(any(Professor.class))).thenReturn(savedProfessor);

        Role professorRole = new Role();
        professorRole.setName("PROFESSOR");

        when(roleRepo.findByName("PROFESSOR")).thenReturn(Optional.of(professorRole));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // act
        Professor result = professorService.createProfessor(dto);

        // assert
        assertNotNull(result);
        assertEquals(dto.getName(), result.getName());
        assertEquals(dto.getEmail(), result.getEmail());
        verify(professorRepo, times(1)).save(any(Professor.class));
        verify(userRepo, times(1)).save(any(User.class));
    }
}
