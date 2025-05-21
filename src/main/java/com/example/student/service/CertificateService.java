package com.example.student.service;

import com.example.student.domain.Certificate;
import com.example.student.domain.Student;
import com.example.student.dto.CertificateDto;
import com.example.student.repo.CertificateRepo;
import com.example.student.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepo certificateRepository;
    private final StudentRepo studentRepository;
    public CertificateService(CertificateRepo certificateRepository,StudentRepo studentRepository) {
        this.certificateRepository=certificateRepository;
        this.studentRepository=studentRepository;
    }
    public List<Certificate> getAll() {
        return certificateRepository.findAll();
    }

    public Certificate create(CertificateDto dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Certificate cert = new Certificate();
        cert.setName(dto.getName());
        cert.setOrganization(dto.getOrganization());
        cert.setIssueDate(dto.getIssueDate());
        cert.setDescription(dto.getDescription());
        cert.setStudent(student);

        return certificateRepository.save(cert);
    }

    public Certificate update(UUID id, CertificateDto dto) {
        Certificate cert = certificateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));

        cert.setName(dto.getName());
        cert.setOrganization(dto.getOrganization());
        cert.setIssueDate(dto.getIssueDate());
        cert.setDescription(dto.getDescription());

        if (dto.getStudentId() != null) {
            Student student = studentRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            cert.setStudent(student);
        }

        return certificateRepository.save(cert);
    }

    public void delete(UUID id) {
        certificateRepository.deleteById(id);
    }

    public Certificate getById(UUID id) {
        return certificateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }
}
