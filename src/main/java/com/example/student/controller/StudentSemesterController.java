package com.example.student.controller;

import com.example.student.domain.Semester;
import com.example.student.domain.SemesterStatus;
import com.example.student.dto.StudentSemesterDto;
import com.example.student.service.StudentSemesterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-semesters")
public class StudentSemesterController {

    private final StudentSemesterService service;

    public StudentSemesterController(StudentSemesterService service) {
        this.service = service;
    }

    @GetMapping("/{studentId}/{status}")
    public List<Semester> getSemestersByStatus(
            @PathVariable String studentId,
            @PathVariable SemesterStatus status) {
        return service.getSemestersByStatus(studentId, status);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody StudentSemesterDto dto) {
        service.registerStudentToSemester(dto);
        return ResponseEntity.ok().build();
    }


}
