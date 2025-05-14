package com.example.student.service;

import com.example.student.domain.Subject;
import com.example.student.dto.SubjectDto;
import com.example.student.repo.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<SubjectDto> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(subject -> new SubjectDto(subject.getId(), subject.getName(), subject.getDepartment(), subject.getCredits()))
                .collect(Collectors.toList());
    }

    public SubjectDto addSubject(SubjectDto subjectDTO) {
        Subject subject = new Subject();
        subject.setName(subjectDTO.getName());
        subject.setDepartment(subjectDTO.getDepartment());
        subject.setCredits(subjectDTO.getCredits());
        Subject savedSubject = subjectRepository.save(subject);
        return new SubjectDto(savedSubject.getId(), savedSubject.getName(), savedSubject.getDepartment(), savedSubject.getCredits());
    }
}