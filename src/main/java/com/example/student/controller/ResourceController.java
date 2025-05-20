package com.example.student.controller;

import com.example.student.dto.ResourceRequestDto;
import com.example.student.dto.ResourceResponseDTO;
import com.example.student.service.ResourceService;
import com.example.student.domain.*;
import com.example.student.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    public ResourceResponseDTO createMaterial(@RequestBody ResourceRequestDto dto) {
        return resourceService.createMaterial(dto);
    }

    @PutMapping("/{id}")
    public ResourceResponseDTO updateMaterial(@PathVariable Long id, @RequestBody ResourceRequestDto dto) {
        return resourceService.updateMaterial(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteMaterial(@PathVariable Long id) {
        resourceService.deleteMaterial(id);
    }

    @GetMapping("/{id}")
    public ResourceResponseDTO getMaterial(@PathVariable Long id) {
        return resourceService.getMaterialById(id);
    }
    @GetMapping
    public List<ResourceResponseDTO> getAllMaterials() {
        return resourceService.getAllMaterials();
    }

}
