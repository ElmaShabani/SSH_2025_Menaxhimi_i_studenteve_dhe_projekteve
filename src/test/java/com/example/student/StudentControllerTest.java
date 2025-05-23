package com.example.student;

import com.example.student.controller.StudentController;
import com.example.student.domain.Student;
import com.example.student.service.StudentService;
import com.example.student.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    public void testGetStudentById() throws Exception {
        Student student = new Student();
        student.setId("321");
        student.setFullname("Studenti Dyte");

        Mockito.when(studentService.getStudent("321")).thenReturn(student);

        mockMvc.perform(get("/students/321"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullname").value("Studenti Dyte"));
    }
}
