package com.example.student.dto;

import java.time.LocalDateTime;

public class ResourceResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String fileUrl;
    private String subjectName;
    private String professorName;
    private LocalDateTime uploadDate;
    private String semesterName;

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getSemesterName() {
        return semesterName;
    }


    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileUrl() {
        return fileUrl;
    }
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getProfessorName() {
        return professorName;
    }
    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }
    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

   
}
