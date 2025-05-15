package com.example.student.service;

import com.example.student.domain.Certificate;
import com.example.student.domain.Reminder;
import com.example.student.domain.Student;
import com.example.student.dto.ReminderDto;
import com.example.student.repo.ReminderRepo;
import com.example.student.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReminderService {

    private final ReminderRepo reminderRepository;
    private final StudentRepo studentRepository;
    public ReminderService(ReminderRepo reminderRepository,StudentRepo studentRepository){
        this.reminderRepository=reminderRepository;
        this.studentRepository=studentRepository;
    }

    public List<Reminder> getAll() {
        return reminderRepository.findAll();
    }

    public Reminder create(ReminderDto dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Reminder reminder = new Reminder();
        reminder.setTitle(dto.getTitle());
        reminder.setMessage(dto.getMessage());
        reminder.setDate(LocalDate.parse(dto.getDate()));
        reminder.setStudent(student);


        return reminderRepository.save(reminder);
    }

    public Reminder update(UUID id, ReminderDto dto) {
        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reminder not found"));

        reminder.setTitle(dto.getTitle());
        reminder.setMessage(dto.getMessage());
        reminder.setDate(LocalDate.parse(dto.getDate()));

        if (dto.getStudentId() != null) {
            Student student = studentRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            reminder.setStudent(student);
        }

        return reminderRepository.save(reminder);
    }


    public void delete(UUID id) {
        reminderRepository.deleteById(id);
    }

    public Reminder getById(UUID id) {
        return reminderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }
}
