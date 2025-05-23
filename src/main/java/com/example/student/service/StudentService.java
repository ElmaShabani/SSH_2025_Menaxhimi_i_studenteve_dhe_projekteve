package com.example.student.service;

import com.example.student.domain.Role;
import com.example.student.domain.Student;
import com.example.student.domain.User;
import com.example.student.repo.RoleRepo;
import com.example.student.repo.StudentRepo;
import com.example.student.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequestMapping("/students")
public class StudentService {


    private final StudentRepo studentRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;


    @Autowired
    public StudentService(StudentRepo studentRepo, UserRepo userRepo, PasswordEncoder passwordEncoder,RoleRepo roleRepo) {
        this.studentRepo = studentRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo=roleRepo;
    }

    public Student createStudent(Student student) {
        Student savedStudent = studentRepo.save(student);

        User user = new User();
        user.setId(savedStudent.getId());
        user.setEmail(savedStudent.getEmail());
        user.setFullname(savedStudent.getFullname());

        Role studentRole = roleRepo.findByName("STUDENT")
                .orElseThrow(() -> new RuntimeException("Roli STUDENT nuk ekziston"));
        user.setRole(studentRole);

        user.setPasswordHash(passwordEncoder.encode(savedStudent.getId()));
        userRepo.save(user);

        return savedStudent;
    }


    @GetMapping
    public Page<Student> getAllStudents(int page, int size) {
        return studentRepo.findAll(PageRequest.of(page, size));
    }

    public Student getStudent(String id) {
        return studentRepo.findById(id).orElse(null);
    }

    public boolean deleteStudent(String id) {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public Student updateStudent(String id, Student updatedStudent) {
        return studentRepo.findById(id).map(student -> {
            student.setFullname(updatedStudent.getFullname());
            student.setDegree(updatedStudent.getDegree());
            student.setDepartment(updatedStudent.getDepartment());
            student.setEmail(updatedStudent.getEmail());
            student.setStudyYear(updatedStudent.getStudyYear());
            student.setUrlPhoto(updatedStudent.getUrlPhoto());
            return studentRepo.save(student);
        }).orElse(null);
    }

    public List<Student> filterStudents(String id, String email) {
        if (id != null && email != null) {
            return studentRepo.findByIdAndEmail(id, email);
        } else if (id != null) {
            return studentRepo.findById(id).map(List::of).orElse(List.of());
        } else if (email != null) {
            return studentRepo.findByEmail(email).map(List::of).orElse(List.of());
        } else {
            return studentRepo.findAll();
        }
    }
}



