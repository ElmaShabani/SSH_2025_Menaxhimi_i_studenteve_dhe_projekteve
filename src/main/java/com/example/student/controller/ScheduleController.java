package com.example.student.controller;

import com.example.student.domain.Schedule;
import com.example.student.service.ScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        Schedule createdSchedule = scheduleService.createSchedule(schedule);
        return ResponseEntity.created(URI.create("/schedules/" + createdSchedule.getId())).body(createdSchedule);
    }

    @GetMapping
    public ResponseEntity<Page<Schedule>> getSchedules(@RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<Schedule> schedules = scheduleService.getAllSchedules(page, size);
        return ResponseEntity.ok().body(schedules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable String id) {
        Schedule schedule = scheduleService.getSchedule(id);
        if (schedule != null) {
            return ResponseEntity.ok().body(schedule);
        }
        return ResponseEntity.notFound().build();
    }
}
