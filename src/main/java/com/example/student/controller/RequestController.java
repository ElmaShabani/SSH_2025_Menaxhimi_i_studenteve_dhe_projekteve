package com.example.student.controller;

import com.example.student.dto.RequestDto;
import com.example.student.domain.RequestStatus;
import com.example.student.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public RequestDto addRequest(@RequestBody RequestDto dto) {
        return requestService.addRequest(dto);
    }

    @GetMapping
    public List<RequestDto> getAllRequests() {
        return requestService.getAllRequests();
    }

    @GetMapping("/{id}")
    public RequestDto getRequest(@PathVariable String id) {
        return requestService.getRequestById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable String id) {
        requestService.deleteRequest(id);
    }

    @PatchMapping("/{id}/status")
    public RequestDto updateStatus(@PathVariable String id, @RequestParam RequestStatus status) {
        return requestService.updateStatus(id, status);
    }
}
