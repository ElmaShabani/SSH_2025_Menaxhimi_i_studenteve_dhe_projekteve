package com.example.student.repo;

import com.example.student.domain.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReminderRepo extends JpaRepository<Reminder, UUID> {
}
