package com.example.student.controller;

import com.example.student.domain.FileUpload;
import com.example.student.repo.FileUploadRepository;
import com.example.student.service.FileStorageService;
import com.example.student.domain.User;
import com.example.student.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @Autowired
    private FileUploadRepository fileUploadRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UserRepo userRepository;

    @PostMapping("/upload")
    public ResponseEntity<FileUpload> uploadFile(@RequestParam("file") MultipartFile file, Principal principal) throws IOException {
        User currentUser = userRepository.findByEmail(principal.getName()).orElseThrow();
        if (!"PROFESSOR".equals(currentUser.getRole())) {
            throw new AccessDeniedException("Vetëm profesorët mund të ngarkojnë skedarë.");
        }

        String fileUrl = fileStorageService.storeFile(file);
        FileUpload upload = new FileUpload();
        upload.setFileName(file.getOriginalFilename());
        upload.setFileUrl(fileUrl);
        upload.setUploadDate(LocalDateTime.now());
        FileUpload saved = fileUploadRepository.save(upload);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> download(@PathVariable String filename, Principal principal) throws MalformedURLException {
        User currentUser = userRepository.findByEmail(principal.getName()).orElseThrow();
        if (!"PROFESSOR".equals(currentUser.getRole()) && !"STUDENT".equals(currentUser.getRole())) {
            throw new AccessDeniedException("Nuk keni të drejtë të shkarkoni këtë skedar.");
        }
        Resource resource = fileStorageService.downloadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id, Principal principal) throws IOException {
        User currentUser = userRepository.findByEmail(principal.getName()).orElseThrow();
        if (!"PROFESSOR".equals(currentUser.getRole())) {
            throw new AccessDeniedException("Vetëm profesorët mund të fshijnë skedarë.");
        }

        FileUpload fileUpload = fileUploadRepository.findById(id).orElseThrow();
        fileStorageService.deleteFile(fileUpload.getFileUrl());
        fileUploadRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}