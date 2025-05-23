package com.example.student.service;

import com.example.student.domain.Professor;
import com.example.student.domain.Subject;
import com.example.student.dto.SubjectDto;
import com.example.student.repo.ProfessorRepo;
import com.example.student.repo.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final ProfessorRepo professorRepo; // duhet injektuar

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, ProfessorRepo professorRepo) {
        this.subjectRepository = subjectRepository;
        this.professorRepo = professorRepo;
    }

    public List<SubjectDto> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(subject -> new SubjectDto(
                        subject.getId(),
                        subject.getName(),
                        subject.getDepartment(),
                        subject.getCredits(),
                        subject.getProfessorId() != null ? subject.getProfessorId().getId().toString() : null
                ))
                .collect(Collectors.toList());
    }

    public SubjectDto addSubject(SubjectDto subjectDTO) {
        Professor professor = professorRepo.findById(UUID.fromString(subjectDTO.getProfessorId()))
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        Subject subject = new Subject();
        subject.setName(subjectDTO.getName());
        subject.setDepartment(subjectDTO.getDepartment());
        subject.setCredits(subjectDTO.getCredits());
        subject.setProfessorId(professor);

        Subject saved = subjectRepository.save(subject);

        return new SubjectDto(
                saved.getId(),
                saved.getName(),
                saved.getDepartment(),
                saved.getCredits(),
                saved.getProfessorId().getId().toString()
        );
    }
}
