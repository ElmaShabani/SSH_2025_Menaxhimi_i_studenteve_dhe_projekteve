package com.example.student.controller;
import com.example.student.domain.Professor;
import com.example.student.dto.ProfessorDto;
import com.example.student.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/professors")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;
    public ProfessorController(ProfessorService professorService){
        this.professorService=professorService;
    }

    @GetMapping
    public ResponseEntity<List<Professor>> getAll() {
        return ResponseEntity.ok(professorService.getAllProfessors());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Professor> create(@RequestBody ProfessorDto dto) {
        return ResponseEntity.ok(professorService.createProfessor(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> update(@PathVariable UUID id, @RequestBody ProfessorDto dto) {
        return ResponseEntity.ok(professorService.updateProfessor(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        professorService.deleteProfessor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(professorService.getProfessorById(id));
    }
}
