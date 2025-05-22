package com.example.student.service;

import com.example.student.domain.*;
import com.example.student.dto.ExamDto;
import com.example.student.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final SubjectRepository subjectRepository;
    private final SemesterRepository semesterRepository;
    private final StudentRepo studentRepository;

    public ExamService(ExamRepository examRepository, SubjectRepository subjectRepository,
                       SemesterRepository semesterRepository, StudentRepo studentRepository) {
        this.examRepository = examRepository;
        this.subjectRepository = subjectRepository;
        this.semesterRepository = semesterRepository;
        this.studentRepository = studentRepository;
    }
    private ExamDto mapToDto(Exam exam) {
        ExamDto dto = new ExamDto();
        dto.setId(exam.getId());
        dto.setTitle(exam.getTitle());
        dto.setDate(exam.getDate());
        dto.setMaxPoints(exam.getMaxPoints());
        dto.setSubjectId(String.valueOf(exam.getSubject().getId())); // Kthe UUID në Long nëse ke kaluar në UUID
        dto.setSemesterId(exam.getSemester().getId());

        List<String> studentIds = new ArrayList<>();
        for (Student student : exam.getStudents()) {
            studentIds.add(student.getId());
        }
        dto.setStudentIds(studentIds);

        return dto;
    }

    public ExamDto addExam(ExamDto dto) {
        if (dto.getSubjectId() == null) {
            throw new IllegalArgumentException("Subject ID must not be null");
        }

        if (dto.getSemesterId() == null) {
            throw new IllegalArgumentException("Semester ID must not be null");
        }

        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new IllegalArgumentException("Subject not found"));

        Semester semester = semesterRepository.findById(dto.getSemesterId())
                .orElseThrow(() -> new IllegalArgumentException("Semester not found"));

        List<Student> students = studentRepository.findAllById(dto.getStudentIds());

        Exam exam = new Exam();
        exam.setTitle(dto.getTitle());
        exam.setDate(dto.getDate());
        exam.setMaxPoints(dto.getMaxPoints());
        exam.setSubject(subject);
        exam.setSemester(semester);
        exam.setStudents(students);

        Exam savedExam = examRepository.save(exam);

        return mapToDto(savedExam);
    }
    public List<ExamDto> getAllExams() {
        List<Exam> exams = examRepository.findAll();
        List<ExamDto> dtos = new ArrayList<>();

        for (Exam exam : exams) {
            ExamDto dto = new ExamDto();
            dto.setId(exam.getId());
            dto.setTitle(exam.getTitle());
            dto.setDate(exam.getDate());
            dto.setMaxPoints(exam.getMaxPoints());
            dto.setSubjectId(String.valueOf(exam.getSubject().getId())); // nëse subject.id është UUID, kjo do dështojë
            dto.setSemesterId(exam.getSemester().getId());

            List<String> studentIds = new ArrayList<>();
            for (Student student : exam.getStudents()) {
                studentIds.add(student.getId());
            }
            dto.setStudentIds(studentIds);

            dtos.add(dto);
        }

        return dtos;
    }

}


