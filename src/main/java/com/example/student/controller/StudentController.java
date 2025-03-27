package com.example.student.controller;

import com.example.student.domain.Student;
import com.example.student.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.created(URI.create("/students/" + createdStudent.getId())).body(createdStudent);
    }

    @GetMapping
    public ResponseEntity<Page<Student>> getStudents(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<Student> students = studentService.getAllStudents(page, size);
        return ResponseEntity.ok().body(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable String id) {
        Student student = studentService.getStudent(id);
        if (student != null) {
            return ResponseEntity.ok().body(student);
        }
        return ResponseEntity.notFound().build();
    }
}
