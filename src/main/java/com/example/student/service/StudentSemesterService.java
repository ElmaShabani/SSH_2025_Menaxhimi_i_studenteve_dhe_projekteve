package com.example.student.service;

import com.example.student.domain.Semester;
import com.example.student.domain.SemesterStatus;
import com.example.student.domain.StudentSemester;
import com.example.student.repo.StudentSemesterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentSemesterService {

    private final StudentSemesterRepository repository;

    public StudentSemesterService(StudentSemesterRepository repository) {
        this.repository = repository;
    }

    public List<Semester> getSemestersByStatus(String studentId, SemesterStatus status) {
        return repository.findByStudentIdAndStatus(studentId, status)
                .stream()
                .map(StudentSemester::getSemester)
                .collect(Collectors.toList());
    }

//    public List<String> getStudentsForSemesterAndYear(int semesterNumber, int yearOfStudy) {
//        return repository.findStudentsBySemesterAndYear(semesterNumber, yearOfStudy);
//    }
}
