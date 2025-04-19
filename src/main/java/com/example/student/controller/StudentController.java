package com.example.student.controller;

import com.example.student.domain.Student;
import com.example.student.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteStudent(@PathVariable String id){
        boolean deleted=studentService.deleteStudent(id);
        if(deleted){
            return ResponseEntity.ok("Student with id"+id+"is deleted successfully");
        }
        else{
            return  ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student updatestudent ){
        Student student= studentService.updateStudent(id,updatestudent);
        if(student!=null){
            return ResponseEntity.ok(student);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/filter")
    public ResponseEntity<List<Student>> filterStudents(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "email", required = false) String email) {

        List<Student> students = studentService.filterStudents(id, email);

        if (!students.isEmpty()) {
            return ResponseEntity.ok(students);
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if no students match the filter
        }
    }
}
