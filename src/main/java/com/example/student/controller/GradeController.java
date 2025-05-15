package com.example.student.controller;

import com.example.student.dto.GradeDto;
import com.example.student.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping
    public ResponseEntity<GradeDto> addGrade(@RequestBody GradeDto gradeDto) {
        return ResponseEntity.ok(gradeService.addGrade(gradeDto));
    }

    @GetMapping
    public ResponseEntity<List<GradeDto>> getAllGrades() {
        return ResponseEntity.ok(gradeService.getAllGrades());
    }
}

