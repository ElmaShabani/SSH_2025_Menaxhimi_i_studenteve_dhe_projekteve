package com.example.student.dto;

public class ResourceRequestDto {
    private String title;
    private String description;

    private String subjectId;       // Long
    private String professorId;     // UUID
    private String semesterId;      // Long
    private String fileUploadId;
    private String semesterName;

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getSemesterName() {
        return semesterName;
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

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public String getFileUploadId() {
        return fileUploadId;
    }

    public void setFileUploadId(String fileUploadId) {
        this.fileUploadId = fileUploadId;
    }
}
