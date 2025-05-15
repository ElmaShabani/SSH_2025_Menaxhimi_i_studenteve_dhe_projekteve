package com.example.student.service;

import com.example.student.domain.Exam;
import com.example.student.domain.Grade;
import com.example.student.domain.Student;
import com.example.student.dto.GradeDto;
import com.example.student.repo.ExamRepository;
import com.example.student.repo.GradeRepository;
import com.example.student.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepo studentRepository;
    private final ExamRepository examRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository, StudentRepo studentRepository, ExamRepository examRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
    }

    public GradeDto addGrade(GradeDto gradeDto) {
        Student student = studentRepository.findById(gradeDto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + gradeDto.getStudentId()));

        Exam exam = examRepository.findById(gradeDto.getExamId())
                .orElseThrow(() -> new RuntimeException("Exam not found with ID: " + gradeDto.getExamId()));

        Grade grade = new Grade();
        grade.setValue(gradeDto.getValue());
        grade.setStudent(student);
        grade.setExam(exam);

        gradeRepository.save(grade);

        gradeDto.setId(grade.getId());
        return gradeDto;
    }

    public List<GradeDto> getAllGrades() {
        return gradeRepository.findAll().stream().map(grade -> {
            GradeDto dto = new GradeDto();
            dto.setId(grade.getId());
            dto.setValue(grade.getValue());
            dto.setStudentId(grade.getStudent().getId());
            dto.setExamId(grade.getExam().getId());
            return dto;
        }).collect(Collectors.toList());
    }
}
