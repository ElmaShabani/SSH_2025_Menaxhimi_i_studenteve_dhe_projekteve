package com.example.student.resource;

import com.example.student.domain.Student;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentResource {

    private final StudentService studentService;

    @Autowired
    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    public Student createStudent(Student student) {
        return studentService.createStudent(student);
    }

    public Student getStudent(String id) {
        return studentService.getStudent(id);
    }
}
