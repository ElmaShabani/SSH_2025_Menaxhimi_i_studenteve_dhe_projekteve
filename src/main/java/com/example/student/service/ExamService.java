package com.example.student.service;

import com.example.student.domain.*;
import com.example.student.dto.ExamDto;
import com.example.student.repo.ExamRepository;
import com.example.student.repo.StudentRepo;
import com.example.student.repo.SubjectRepository;
import com.example.student.repo.StudentSemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepo studentRepository;
    private final StudentSemesterRepository studentSemesterRepository;

    @Autowired
    public ExamService(ExamRepository examRepository, SubjectRepository subjectRepository,
                       StudentRepo studentRepository, StudentSemesterRepository studentSemesterRepository) {
        this.examRepository = examRepository;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.studentSemesterRepository = studentSemesterRepository;
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
            dto.setSubjectId(Long.valueOf(exam.getSubject().getId()));

            List<String> studentIds = new ArrayList<>();
            for (Student student : exam.getStudents()) {
                studentIds.add(student.getId());
            }
            dto.setStudentIds(studentIds);

            if (exam.getSemester() != null) {
                dto.setSemesterId(Long.valueOf(exam.getSemester().getId().toString()));
            }

            dtos.add(dto);
        }

        return dtos;
    }

    public ExamDto addExam(ExamDto dto) {
        // VALIDIME TË INPUTIT
        if (dto.getSubjectId() == null) {
            throw new IllegalArgumentException("Subject ID must not be null");
        }

        if (dto.getStudentIds() == null || dto.getStudentIds().isEmpty()) {
            throw new IllegalArgumentException("Student list must not be empty");
        }

        // Lidh subjektin
        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject with ID " + dto.getSubjectId() + " not found"));

        // Lidh studentët
        List<Student> students = studentRepository.findAllById(dto.getStudentIds());
        if (students.isEmpty()) {
            throw new RuntimeException("No valid students found with the given IDs");
        }

        // Krijo objektin Exam
        Exam exam = new Exam();
        exam.setTitle(dto.getTitle());
        exam.setDate(dto.getDate());
        exam.setMaxPoints(dto.getMaxPoints());
        exam.setSubject(subject);
        exam.setStudents(students);

        // Merr semestrin nga studenti i parë
        String studentId = students.get(0).getId();
        List<StudentSemester> studentSemesters = studentSemesterRepository
                .findByStudentIdAndStatus(studentId, SemesterStatus.NE_VAZHDIM);

        if (studentSemesters.isEmpty()) {
            throw new RuntimeException("Student with ID " + studentId + " has no active semester");
        }

        Semester semester = studentSemesters.get(0).getSemester();
        exam.setSemester(semester);

        // Ruaj dhe kthe DTO-në me ID të përditësuar
        Exam saved = examRepository.save(exam);
        dto.setId(saved.getId());
        dto.setSemesterId(semester.getId()); // opsionale: për ta pasur në përgjigje

        return dto;
    }
}
