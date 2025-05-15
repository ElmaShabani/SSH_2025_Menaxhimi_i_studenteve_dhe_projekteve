package com.example.student.service;

import com.example.student.domain.Department;
import com.example.student.dto.DepartmentDto;
import com.example.student.repo.DepartmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;




@Service
public class DepartmentService {
    private final DepartmentRepo repository;

    public DepartmentService(DepartmentRepo repository) {
        this.repository = repository;
    }

    public List<DepartmentDto> getAllDepartments() {
        return repository.findAll().stream()
                .map(dep -> new DepartmentDto(dep.getId(), dep.getName()))
                .collect(Collectors.toList());
    }

    public DepartmentDto addDepartment(DepartmentDto dto) {
        Department dep = new Department();
        dep.setName(dto.getName());
        Department saved = repository.save(dep);
        return new DepartmentDto(saved.getId(), saved.getName());
    }

    public void deleteDepartment(Long id) {
        repository.deleteById(id);
    }
}
