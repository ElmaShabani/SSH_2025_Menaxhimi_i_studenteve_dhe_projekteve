package com.example.student.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private Integer credits;
}

