package com.example.student.service;

import com.example.student.dto.RequestDto;
import com.example.student.domain.Request;
import com.example.student.domain.RequestStatus;
import com.example.student.domain.Student;
import com.example.student.repo.RequestRepository;
import com.example.student.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final StudentRepo studentRepository;

    public RequestService(RequestRepository requestRepository, StudentRepo studentRepository) {
        this.requestRepository = requestRepository;
        this.studentRepository = studentRepository;
    }
    public RequestDto addRequest(RequestDto dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Request request = new Request();
        request.setTitle(dto.getTitle());
        request.setDescription(dto.getDescription());
        request.setDateCreated(LocalDate.now());
        request.setStatus(RequestStatus.PENDING);
        request.setStudent(student);

        request = requestRepository.save(request);

        dto.setId(request.getId());
        dto.setDateCreated(request.getDateCreated());
        dto.setStatus(request.getStatus());

        return dto;
    }

    public List<RequestDto> getAllRequests() {
        return requestRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Optional<RequestDto> getRequestById(String id) {
        return requestRepository.findById(id).map(this::mapToDto);
    }

    public void deleteRequest(String id) {
        requestRepository.deleteById(id);
    }

    public RequestDto updateStatus(String id, RequestStatus newStatus) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(newStatus);
        requestRepository.save(request);

        return mapToDto(request);
    }

    private RequestDto mapToDto(Request request) {
        RequestDto dto = new RequestDto();
        dto.setId(request.getId());
        dto.setTitle(request.getTitle());
        dto.setDescription(request.getDescription());
        dto.setDateCreated(request.getDateCreated());
        dto.setStatus(request.getStatus());
        dto.setStudentId(request.getStudent().getId());
        return dto;
    }
}
