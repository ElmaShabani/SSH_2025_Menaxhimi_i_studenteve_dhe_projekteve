package com.example.student.service;

import com.example.student.domain.FileUpload;
import com.example.student.repo.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FileUploadService {

    private final String UPLOAD_DIR = "uploads/";

    @Autowired
    private FileUploadRepository fileUploadRepository;

    public Long storeFile(MultipartFile file) {
        try {
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + filename);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            FileUpload fileUpload = new FileUpload();
            fileUpload.setFileUrl(path.toString());
            fileUpload.setUploadDate(LocalDateTime.now());
            fileUpload.setOriginalFileName(file.getOriginalFilename());

            fileUpload = fileUploadRepository.save(fileUpload);
            return fileUpload.getId();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    public ResponseEntity<byte[]> downloadFile(Long id) {
        FileUpload fileUpload = fileUploadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));

        try {
            Path path = Paths.get(fileUpload.getFileUrl());
            byte[] data = Files.readAllBytes(path);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition
                    .attachment()
                    .filename(fileUpload.getOriginalFileName())
                    .build());

            return new ResponseEntity<>(data, headers, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException("Failed to download file", e);
        }
    }
}
