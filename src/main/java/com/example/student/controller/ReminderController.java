package com.example.student.controller;

import com.example.student.domain.Reminder;
import com.example.student.dto.ReminderDto;
import com.example.student.repo.StudentRepo;
import com.example.student.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reminders")
//@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;

    public ReminderController (ReminderService reminderService) {
        this.reminderService= reminderService;
    }

    @GetMapping
    public ResponseEntity<List<Reminder>> getAll() {
        return ResponseEntity.ok(reminderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reminder> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(reminderService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Reminder> create(@RequestBody ReminderDto dto) {
        return ResponseEntity.ok(reminderService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reminder> update(@PathVariable UUID id, @RequestBody ReminderDto dto) {
        return ResponseEntity.ok(reminderService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        reminderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
