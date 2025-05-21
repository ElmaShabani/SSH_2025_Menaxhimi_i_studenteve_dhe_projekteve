package com.example.student.repo;
import com.example.student.domain.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CertificateRepo extends JpaRepository<Certificate, UUID> {
}
