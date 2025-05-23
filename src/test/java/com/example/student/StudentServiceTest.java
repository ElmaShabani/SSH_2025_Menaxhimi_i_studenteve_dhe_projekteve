package com.example.student;

import com.example.student.domain.Role;
import com.example.student.domain.Student;
import com.example.student.domain.User;
import com.example.student.repo.RoleRepo;
import com.example.student.repo.StudentRepo;
import com.example.student.repo.UserRepo;
import com.example.student.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @Mock
    private StudentRepo studentRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepo roleRepo;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStudent() {
        Student student = new Student();
        student.setId("student123");
        student.setEmail("student@example.com");
        student.setFullname("Student Test");

        Role role = new Role();
        role.setName("STUDENT");

        when(studentRepo.save(any(Student.class))).thenReturn(student);
        when(roleRepo.findByName("STUDENT")).thenReturn(Optional.of(role));
        when(passwordEncoder.encode("student123")).thenReturn("hashedPassword");

        Student savedStudent = studentService.createStudent(student);

        assertNotNull(savedStudent);
        assertEquals("student123", savedStudent.getId());
        assertEquals("student@example.com", savedStudent.getEmail());

        verify(studentRepo, times(1)).save(student);
        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    void testCreateStudentRoleNotFound() {
        Student student = new Student();
        student.setId("student123");
        student.setEmail("student@example.com");

        when(studentRepo.save(any(Student.class))).thenReturn(student);
        when(roleRepo.findByName("STUDENT")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            studentService.createStudent(student);
        });

        assertEquals("Roli STUDENT nuk ekziston", exception.getMessage());
    }
}
