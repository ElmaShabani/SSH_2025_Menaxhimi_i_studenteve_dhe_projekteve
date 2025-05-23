package com.example.student;

import com.example.student.controller.UserController;
import com.example.student.domain.Role;
import com.example.student.domain.User;
import com.example.student.dto.PasswordChangeDto;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @WithMockUser(username = "user@example.com")
    @Test
    public void testGetUserById() throws Exception {
        User user = new User();
        user.setId("123");
        user.setFullname("Besarta Mustafa");

        Mockito.when(userService.findById("123")).thenReturn(Optional.of(user));

        mockMvc.perform(get("/users/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.fullname").value("Besarta Mustafa"));
    }
}
