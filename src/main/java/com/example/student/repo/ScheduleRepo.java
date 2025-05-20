package com.example.student.repo;

import com.example.student.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, String> {
    Optional<Schedule> findById(String id);
}
