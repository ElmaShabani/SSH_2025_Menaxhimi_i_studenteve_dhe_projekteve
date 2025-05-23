package com.example.student.dto;

import java.time.LocalDateTime;

public class SubmissionCreateDTO {

    private String studentId;
    private String assignmentId;
    private String fileUrl;
    private LocalDateTime submittedAt;
    private String comment;

    // Getters & Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
