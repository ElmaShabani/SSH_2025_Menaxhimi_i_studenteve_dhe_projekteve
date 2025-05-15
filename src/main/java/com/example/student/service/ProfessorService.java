package com.example.student.service;
import com.example.student.domain.Professor;
import com.example.student.dto.ProfessorDto;
import com.example.student.repo.ProfessorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepo professorRepository;
    public ProfessorService(ProfessorRepo professorRepository){
        this.professorRepository=professorRepository;
    }
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    public Professor createProfessor(ProfessorDto dto) {
        Professor professor = new Professor();
        professor.setName(dto.getName());
        professor.setEmail(dto.getEmail());
        professor.setTitle(dto.getTitle());
        return professorRepository.save(professor);
    }

    public void deleteProfessor(UUID id) {
        professorRepository.deleteById(id);
    }

    public Professor updateProfessor(UUID id, ProfessorDto dto) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor not found"));
        professor.setName(dto.getName());
        professor.setEmail(dto.getEmail());
        professor.setTitle(dto.getTitle());
        return professorRepository.save(professor);
    }

    public Professor getProfessorById(UUID id) {
        return professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
