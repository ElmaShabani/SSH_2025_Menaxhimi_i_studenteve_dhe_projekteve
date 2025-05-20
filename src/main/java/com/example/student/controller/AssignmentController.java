package com.example.student.controller;

import com.example.student.dto.AssignmentDto;
import com.example.student.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public AssignmentDto createAssignment(@RequestBody AssignmentDto dto) {
        return assignmentService.addAssignment(dto);
    }

    @GetMapping
    public List<AssignmentDto> getAll() {
        return assignmentService.getAllAssignments();
    }
}
