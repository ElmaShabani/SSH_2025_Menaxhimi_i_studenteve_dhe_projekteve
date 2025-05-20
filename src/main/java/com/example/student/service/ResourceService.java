package com.example.student.service;

import com.example.student.domain.*;
import com.example.student.dto.ResourceRequestDto;
import com.example.student.dto.ResourceResponseDTO;
import com.example.student.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepo resourceRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ProfessorRepo professorRepository;

    @Autowired
    private StudentSemesterRepository semesterRepository;

    @Autowired
    private FileUploadRepository fileUploadRepository;

    public ResourceResponseDTO createMaterial(ResourceRequestDto dto) {
        Resource resource = new Resource();
        resource.setTitle(dto.getTitle());
        resource.setDescription(dto.getDescription());

        // Konvertimi i ID-ve sipas nevojës
        Subject subject = subjectRepository.findById(Long.parseLong(dto.getSubjectId())).orElseThrow();
        Professor professor = professorRepository.findById(UUID.fromString(dto.getProfessorId())).orElseThrow();
        StudentSemester studentSemester = semesterRepository.findById(Long.parseLong(dto.getSemesterId())).orElseThrow();
        Semester semester = studentSemester.getSemester();
        resource.setSemester(semester);
        FileUpload fileUpload = fileUploadRepository.findById(Long.parseLong(dto.getFileUploadId())).orElseThrow();

        resource.setSubject(subject);
        resource.setProfessor(professor);
        resource.setSemester(semester);
        resource.setFileUpload(fileUpload);
        resource.setUploadDate(LocalDateTime.now());

        Resource saved = resourceRepository.save(resource);
        return mapToResponse(saved);
    }
    private ResourceResponseDTO mapToResponseDto(Resource resource) {
        ResourceResponseDTO dto = new ResourceResponseDTO();
        dto.setId(resource.getId());
        dto.setTitle(resource.getTitle());
        dto.setDescription(resource.getDescription());
        dto.setUploadDate(resource.getUploadDate());

        if (resource.getSubject() != null) {
            dto.setSubjectName(resource.getSubject().getName());
        }

        if (resource.getProfessor() != null) {
            dto.setProfessorName(resource.getProfessor().getName());
        }

        if (resource.getSemester() != null) {
            dto.setSemesterName(resource.getSemester().getName());
        }

        if (resource.getFileUpload() != null) {
            dto.setFileUrl(resource.getFileUpload().getFileUrl());
        }

        return dto;
    }


    public ResourceResponseDTO updateMaterial(Long id, ResourceRequestDto dto) {
        Resource resource = resourceRepository.findById(id).orElseThrow();

        resource.setTitle(dto.getTitle());
        resource.setDescription(dto.getDescription());

        resource.setSubject(subjectRepository.findById(Long.parseLong(dto.getSubjectId())).orElseThrow());
        resource.setProfessor(professorRepository.findById(UUID.fromString(dto.getProfessorId())).orElseThrow());

        StudentSemester studentSemester = semesterRepository.findById(Long.parseLong(dto.getSemesterId())).orElseThrow();
        resource.setSemester(studentSemester.getSemester());

        resource.setFileUpload(fileUploadRepository.findById(Long.parseLong(dto.getFileUploadId())).orElseThrow());

        Resource updated = resourceRepository.save(resource);
        return mapToResponse(updated);
    }


    public void deleteMaterial(Long id) {
        resourceRepository.deleteById(id);
    }

    public ResourceResponseDTO getMaterialById(Long id) {
        return mapToResponse(resourceRepository.findById(id).orElseThrow());
    }

    private ResourceResponseDTO mapToResponse(Resource resource) {
        ResourceResponseDTO dto = new ResourceResponseDTO();
        dto.setId(resource.getId());
        dto.setTitle(resource.getTitle());
        dto.setDescription(resource.getDescription());
        dto.setFileUrl(resource.getFileUpload().getFileUrl());
        dto.setSubjectName(resource.getSubject().getName());
        dto.setProfessorName(resource.getProfessor().getName());
        dto.setSemesterName(resource.getSemester().getName());
        dto.setUploadDate(resource.getUploadDate());
        return dto;
    }
    public List<ResourceResponseDTO> getAllMaterials() {
        List<Resource> materials = resourceRepository.findAll();
        return materials.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

}
