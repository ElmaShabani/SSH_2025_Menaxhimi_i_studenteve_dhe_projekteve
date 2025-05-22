package com.example.student.service;

import com.example.student.domain.Semester;
import com.example.student.repo.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    public Semester createSemester(Semester semester) {
        return semesterRepository.save(semester);
    }

    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    public Optional<Semester> getSemesterById(Long id) {
        return semesterRepository.findById(id);
    }

    public Semester updateSemester(Long id, Semester updatedSemester) {
        return semesterRepository.findById(id).map(existing -> {
            existing.setName(updatedSemester.getName());
            existing.setAcademicYear(updatedSemester.getAcademicYear());
            existing.setSemesterNumber(updatedSemester.getSemesterNumber());
            existing.setYearOfStudy(updatedSemester.getYearOfStudy());
            existing.setStartDate(updatedSemester.getStartDate());
            existing.setEndDate(updatedSemester.getEndDate());
            return semesterRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Semester not found with id " + id));
    }

    public void deleteSemester(Long id) {
        semesterRepository.deleteById(id);
    }
}
