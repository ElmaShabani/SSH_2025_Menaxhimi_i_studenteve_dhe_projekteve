package com.example.student.domain;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;         // Emri i certifikatës
    private String organization; // Kush e lëshoi
    private String issueDate;    // Data e lëshimit (mundesh edhe me bo LocalDate nëse e do me datë të saktë)
    private String description;  // Përshkrim opsional

    // Nëse do me lidh me student
    @ManyToOne
    private Student student;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
