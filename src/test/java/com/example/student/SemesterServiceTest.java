package com.example.student;

import com.example.student.domain.Semester;
import com.example.student.repo.SemesterRepository;
import com.example.student.service.SemesterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SemesterServiceTest {

    @Mock
    private SemesterRepository semesterRepository;

    @InjectMocks
    private SemesterService semesterService;

    private Semester semester;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        semester = new Semester();
        semester.setId(1L);
        semester.setName("Vjeshtë 2024");
        semester.setAcademicYear("2024/2025");
        semester.setSemesterNumber(1);
        semester.setYearOfStudy(2);
        semester.setStartDate(LocalDate.of(2024, 9, 1));
        semester.setEndDate(LocalDate.of(2025, 1, 31));
    }

    @Test
    void testCreateSemester() {
        when(semesterRepository.save(any(Semester.class))).thenReturn(semester);

        Semester created = semesterService.createSemester(semester);

        assertNotNull(created);
        assertEquals("Vjeshtë 2024", created.getName());
    }

    @Test
    void testGetAllSemesters() {
        when(semesterRepository.findAll()).thenReturn(List.of(semester));

        List<Semester> result = semesterService.getAllSemesters();

        assertEquals(1, result.size());
        assertEquals("Vjeshtë 2024", result.get(0).getName());
    }

    @Test
    void testGetSemesterById() {
        when(semesterRepository.findById(1L)).thenReturn(Optional.of(semester));

        Optional<Semester> result = semesterService.getSemesterById(1L);

        assertTrue(result.isPresent());
        assertEquals("Vjeshtë 2024", result.get().getName());
    }

    @Test
    void testUpdateSemester() {
        Semester updated = new Semester();
        updated.setName("Pranverë 2025");
        updated.setAcademicYear("2024/2025");
        updated.setSemesterNumber(2);
        updated.setYearOfStudy(2);
        updated.setStartDate(LocalDate.of(2025, 2, 1));
        updated.setEndDate(LocalDate.of(2025, 6, 30));

        when(semesterRepository.findById(1L)).thenReturn(Optional.of(semester));
        when(semesterRepository.save(any(Semester.class))).thenAnswer(i -> i.getArgument(0));

        Semester result = semesterService.updateSemester(1L, updated);

        assertEquals("Pranverë 2025", result.getName());
        assertEquals(2, result.getSemesterNumber());
    }

    @Test
    void testDeleteSemester() {
        doNothing().when(semesterRepository).deleteById(1L);

        assertDoesNotThrow(() -> semesterService.deleteSemester(1L));
        verify(semesterRepository, times(1)).deleteById(1L);
    }
}
