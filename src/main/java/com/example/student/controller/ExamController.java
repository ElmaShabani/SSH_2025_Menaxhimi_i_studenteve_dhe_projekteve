package com.example.student.controller;

import com.example.student.dto.ExamDto;
import com.example.student.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {

    private final ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    // Merr të gjitha provimet
    @GetMapping
    public ResponseEntity<List<ExamDto>> getExams() {
        return ResponseEntity.ok(examService.getAllExams());
    }

    // Shto provim të ri
    @PostMapping
    public ResponseEntity<ExamDto> addExam(@RequestBody ExamDto examDto) {
        return ResponseEntity.ok(examService.addExam(examDto));
    }
}
