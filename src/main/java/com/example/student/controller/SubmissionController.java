package com.example.student.controller;

import com.example.student.dto.SubmissionCreateDTO;
import com.example.student.dto.SubmissionResponseDTO;
import com.example.student.service.SubmissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submissions")
@CrossOrigin // nëse e përdor frontend-in diku tjetër
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @PostMapping
    public SubmissionResponseDTO createSubmission(@RequestBody SubmissionCreateDTO dto) {
        return submissionService.createSubmission(dto);
    }
}

