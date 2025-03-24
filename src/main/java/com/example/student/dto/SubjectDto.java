package com.example.student.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {
        private Long id;
        private String name;
        private String department;
        private Integer credits;
}

