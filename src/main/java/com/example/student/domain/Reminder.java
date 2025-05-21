package com.example.student.domain;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String message;
    private LocalDate date; // Data kur duhet me u kujtu

    @ManyToOne
    private Student student; // Kujt i përket kujtesa

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

