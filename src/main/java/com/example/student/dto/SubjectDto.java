package com.example.student.dto;

import com.example.student.domain.Professor;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {
        private String id;
        private String name;
        private String department;
        private Integer credits;
        private String professorId;

        public SubjectDto(String id, String name, String department, Integer credits, String professorId) {
                this.id=id;
                this.name = name;
                this.department = department;
                this.credits = credits;
                this.professorId=professorId;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getDepartment() {
                return department;
        }

        public void setDepartment(String department) {
                this.department = department;
        }

        public Integer getCredits() {
                return credits;
        }

        public void setCredits(Integer credits) {
                this.credits = credits;
        }

        public String getProfessorId() {
                return professorId;
        }

        public void setProfessorId(String professorId) {
                this.professorId = professorId;
        }
}
