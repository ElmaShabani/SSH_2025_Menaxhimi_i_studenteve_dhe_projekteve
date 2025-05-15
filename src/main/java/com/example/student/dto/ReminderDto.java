package com.example.student.dto;
import lombok.Data;
@Data
public class ReminderDto {
    private String title;
    private String message;
    private String date;      // Format: yyyy-MM-dd ose përdor LocalDate nëse frontend e kthen drejt
    private String studentId; // UUID si tekst

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
