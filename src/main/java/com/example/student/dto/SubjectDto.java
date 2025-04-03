package com.example.student.dto;

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
        public SubjectDto(String id,String name, String department, Integer credits) {
                this.id=id;
                this.name = name;
                this.department = department;
                this.credits = credits;
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
}
