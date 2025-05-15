package com.example.student.controller;
import com.example.student.dto.DepartmentDto;
import com.example.student.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*") // Për frontend
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<DepartmentDto> getAll() {
        return service.getAllDepartments();
    }

    @PostMapping
    public DepartmentDto create(@RequestBody DepartmentDto dto) {
        return service.addDepartment(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteDepartment(id);
    }
}
