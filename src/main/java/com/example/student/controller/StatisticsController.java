package com.example.student.controller;

import com.example.student.repo.DepartmentRepo;
import com.example.student.repo.ProfessorRepo;
import com.example.student.repo.SubjectRepository;
import com.example.student.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class StatisticsController {

    private final UserRepo userRepo;
    private final ProfessorRepo professorRepo;
    private final SubjectRepository subjectRepository;
    private final DepartmentRepo departmentRepo;

    public StatisticsController(UserRepo userRepo, ProfessorRepo professorRepo, SubjectRepository subjectRepository, DepartmentRepo departmentRepo) {
        this.userRepo = userRepo;
        this.professorRepo = professorRepo;
        this.subjectRepository = subjectRepository;
        this.departmentRepo = departmentRepo;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/stats")
    public ResponseEntity<?> getStatistics() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("students", userRepo.countByRoleName("STUDENT"));
        stats.put("professors", professorRepo.count());
        stats.put("courses", subjectRepository.count());
        stats.put("departments", departmentRepo.count());

        return ResponseEntity.ok(stats);
    }
}
