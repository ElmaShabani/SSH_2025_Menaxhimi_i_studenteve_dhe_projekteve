package com.example.student.service;

import com.example.student.domain.Schedule;
import com.example.student.repo.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private final ScheduleRepo scheduleRepo;

    @Autowired
    public ScheduleService(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepo.save(schedule);
    }

    public Page<Schedule> getAllSchedules(int page, int size) {
        return scheduleRepo.findAll(PageRequest.of(page, size));
    }

    public Schedule getSchedule(String id) {
        return scheduleRepo.findById(id).orElse(null);
    }

    public boolean deleteSchedule(String id) {
        if (scheduleRepo.existsById(id)) {
            scheduleRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
