package com.example.student.service;

import com.example.student.domain.Attendance;
import com.example.student.repo.AttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepo attendanceRepo;

    public Attendance createAttendance(Attendance attendance) {
        return attendanceRepo.save(attendance);
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepo.findAll();
    }

    public Optional<Attendance> getAttendance(String id) {
        return attendanceRepo.findById(id);
    }

    public boolean deleteAttendance(String id) {
        if (attendanceRepo.existsById(id)) {
            attendanceRepo.deleteById(id);
            return true;
        }
        return false;
    }
}

