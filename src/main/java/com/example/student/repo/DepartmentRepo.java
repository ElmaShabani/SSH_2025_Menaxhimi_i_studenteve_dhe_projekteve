package com.example.student.repo;
import com.example.student.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    boolean existsByName(String name);
}

