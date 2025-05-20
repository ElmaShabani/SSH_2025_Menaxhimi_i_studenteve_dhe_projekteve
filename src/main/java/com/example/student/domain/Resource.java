package com.example.student.domain;

import jakarta.persistence.*;
import com.example.student.domain.*;
import com.example.student.service.*;
import java.time.LocalDateTime;

@Entity
public class Resource {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;
        private String description;
        private LocalDateTime uploadDate;

        @ManyToOne
        private Subject subject;

        @ManyToOne
        private Professor professor;

        @ManyToOne
        private Semester semester;
        @ManyToOne
        @JoinColumn(name = "file_upload_id")
        private FileUpload fileUpload;


        // Getters and setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public LocalDateTime getUploadDate() { return uploadDate; }
        public void setUploadDate(LocalDateTime uploadDate) { this.uploadDate = uploadDate; }

        public Subject getSubject() { return subject; }
        public void setSubject(Subject subject) { this.subject = subject; }

        public Professor getProfessor() { return professor; }
        public void setProfessor(Professor professor) { this.professor = professor; }

        public Semester getSemester() { return semester; }
        public void setSemester(Semester semester) { this.semester = semester; }

        public FileUpload getFileUpload() { return fileUpload; }
        public void setFileUpload(FileUpload fileUpload) { this.fileUpload = fileUpload; }
}
