package com.example.student.repo;

import com.example.student.domain.StudentSemester;
import com.example.student.domain.SemesterStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentSemesterRepository extends JpaRepository<StudentSemester, Long> {
    List<StudentSemester> findByStudentIdAndStatus(String studentId, SemesterStatus status);
//    @Query("SELECT DISTINCT ss.student.fullname FROM StudentSemester ss WHERE ss.semester.semesterNumber = :semesterNumber AND ss.semester.yearOfStudy = :yearOfStudy")
//    List<String> findStudentsBySemesterAndYear(@Param("semesterNumber") int semesterNumber, @Param("yearOfStudy") int yearOfStudy);
}
