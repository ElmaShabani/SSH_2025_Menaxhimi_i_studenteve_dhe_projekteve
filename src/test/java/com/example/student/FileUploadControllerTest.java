package com.example.student;

import com.example.student.controller.FileUploadController;
import com.example.student.domain.FileUpload;
import com.example.student.domain.Role;
import com.example.student.domain.User;
import com.example.student.repo.FileUploadRepository;
import com.example.student.repo.UserRepo;
import com.example.student.service.FileStorageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FileUploadController.class)
public class FileUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileUploadRepository fileUploadRepository;

    @MockBean
    private FileStorageService fileStorageService;

    @MockBean
    private UserRepo userRepository;



    @WithMockUser(username = "student@example.com", roles = {"STUDENT"})
    @Test
    public void testDownloadFileAsStudent() throws Exception {
        User student = new User();
        student.setEmail("student@example.com");
        Role studentRole = new Role();
        studentRole.setName("STUDENT");
        student.setRole(studentRole);


        when(userRepository.findByEmail("student@example.com")).thenReturn(Optional.of(student));
        when(fileStorageService.downloadFile("file.txt"))
                .thenReturn(new ByteArrayResource("content".getBytes()));

        mockMvc.perform(get("/api/files/download/file.txt"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=\"file.txt\""));
    }


}
