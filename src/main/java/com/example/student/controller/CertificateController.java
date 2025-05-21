package com.example.student.controller;

import com.example.student.domain.Certificate;
import com.example.student.dto.CertificateDto;
import com.example.student.service.CertificateService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    public ResponseEntity<List<Certificate>> getAll() {
        return ResponseEntity.ok(certificateService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(certificateService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Certificate> create(@RequestBody CertificateDto dto) {
        return ResponseEntity.ok(certificateService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certificate> update(@PathVariable UUID id, @RequestBody CertificateDto dto) {
        return ResponseEntity.ok(certificateService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        certificateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
