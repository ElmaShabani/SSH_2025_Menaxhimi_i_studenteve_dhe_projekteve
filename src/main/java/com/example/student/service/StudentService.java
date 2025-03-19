package com.example.student.service;

import com.example.student.domain.Student;
import com.example.student.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    public Page<Student> getAllStudents(int page, int size) {
        return studentRepo.findAll(PageRequest.of(page, size));
    }

    public Student getStudent(String id) {
        return studentRepo.findById(id).orElse(null);
    }
}
