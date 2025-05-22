package com.example.student.service;
import com.example.student.domain.Professor;
import com.example.student.domain.Role;
import com.example.student.domain.User;
import com.example.student.dto.ProfessorDto;
import com.example.student.repo.ProfessorRepo;
import com.example.student.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepo professorRepository;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    public ProfessorService(ProfessorRepo professorRepository, UserRepo userRepo,PasswordEncoder passwordEncoder) {
        this.professorRepository = professorRepository;
        this.userRepo = userRepo;
        this.passwordEncoder=passwordEncoder;
    }

    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    public Professor createProfessor(ProfessorDto dto) {
        Professor professor = new Professor();
        UUID profId = UUID.randomUUID();
        professor.setId(profId);
        professor.setName(dto.getName());
        professor.setEmail(dto.getEmail());
        professor.setTitle(dto.getTitle());

        Professor savedProf = professorRepository.save(professor);

        User user = new User();
        user.setId(savedProf.getId().toString());
        user.setEmail(savedProf.getEmail());
        user.setFullname(savedProf.getName());
        user.setRole(Role.PROFESSOR);
        user.setPasswordHash(passwordEncoder.encode(savedProf.getId().toString()));
        userRepo.save(user);

        return savedProf;
    }

    public void deleteProfessor(UUID id) {
        professorRepository.deleteById(id);
        userRepo.deleteById(id.toString());
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