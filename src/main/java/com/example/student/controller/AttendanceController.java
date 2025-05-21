package com.example.student.controller;

import com.example.student.domain.Attendance;
import com.example.student.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@PreAuthorize("hasRole('PROFESSOR')")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping
    public Attendance createAttendance(@RequestBody Attendance attendance) {
        return attendanceService.createAttendance(attendance);
    }

    @GetMapping
    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    @GetMapping("/{id}")
    public Attendance getAttendance(@PathVariable String id) {
        return attendanceService.getAttendance(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public boolean deleteAttendance(@PathVariable String id) {
        return attendanceService.deleteAttendance(id);
    }
}

