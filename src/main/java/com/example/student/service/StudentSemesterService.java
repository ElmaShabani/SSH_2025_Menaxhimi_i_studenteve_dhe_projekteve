package com.example.student.service;

import com.example.student.domain.Semester;
import com.example.student.domain.SemesterStatus;
import com.example.student.domain.Student;
import com.example.student.domain.StudentSemester;
import com.example.student.dto.StudentSemesterDto;
import com.example.student.repo.SemesterRepository;
import com.example.student.repo.StudentRepo;
import com.example.student.repo.StudentSemesterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentSemesterService {

    private final StudentRepo studentRepository;
    private final SemesterRepository semesterRepository;
    private final StudentSemesterRepository studentSemesterRepository;

    public StudentSemesterService(StudentRepo studentRepository,
                                  SemesterRepository semesterRepository,
                                  StudentSemesterRepository studentSemesterRepository) {
        this.studentRepository = studentRepository;
        this.semesterRepository = semesterRepository;
        this.studentSemesterRepository = studentSemesterRepository;
    }

    public List<Semester> getSemestersByStatus(String studentId, SemesterStatus status) {
        return studentSemesterRepository.findByStudentIdAndStatus(studentId, status)
                .stream()
                .map(StudentSemester::getSemester)
                .collect(Collectors.toList());
    }

    public void registerStudentToSemester(StudentSemesterDto dto) {
        if (dto.getStudentId() == null || dto.getSemesterId() == null) {
            throw new IllegalArgumentException("StudentId dhe SemesterId nuk duhet të jenë null.");
        }

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student nuk u gjet me ID: " + dto.getStudentId()));

        Semester semester = semesterRepository.findById(dto.getSemesterId())
                .orElseThrow(() -> new RuntimeException("Semestri nuk u gjet me ID: " + dto.getSemesterId()));

        SemesterStatus status;
        try {
            status = SemesterStatus.valueOf(dto.getStatus() != null ? dto.getStatus() : "NE_VAZHDIM");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Status i pavlefshëm: " + dto.getStatus());
        }

        StudentSemester ss = new StudentSemester();
        ss.setStudent(student);
        ss.setSemester(semester);
        ss.setStatus(status);

        studentSemesterRepository.save(ss);
    }

}
