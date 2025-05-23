package com.example.student;

import com.example.student.domain.Exam;
import com.example.student.domain.Grade;
import com.example.student.domain.Student;
import com.example.student.dto.GradeDto;
import com.example.student.repo.ExamRepository;
import com.example.student.repo.GradeRepository;
import com.example.student.repo.StudentRepo;
import com.example.student.service.GradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GradeServiceTest {

    @Mock
    private GradeRepository gradeRepository;

    @Mock
    private StudentRepo studentRepository;

    @Mock
    private ExamRepository examRepository;

    @InjectMocks
    private GradeService gradeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllGrades() {
        Student student = new Student();
        student.setId("student1");

        Exam exam = new Exam();
        exam.setId(1L);

        Grade grade = new Grade();
        grade.setId(10L);
        grade.setValue(95.0);
        grade.setStudent(student);
        grade.setExam(exam);

        when(gradeRepository.findAll()).thenReturn(List.of(grade));

        List<GradeDto> grades = gradeService.getAllGrades();

        assertEquals(1, grades.size());
        GradeDto dto = grades.get(0);
        assertEquals(10L, dto.getId());
        assertEquals(95.0, dto.getValue());
        assertEquals("student1", dto.getStudentId());
        assertEquals(1L, dto.getExamId());
    }
}
