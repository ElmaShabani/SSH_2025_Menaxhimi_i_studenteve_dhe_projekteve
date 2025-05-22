package com.example.student.loader;

import com.example.student.domain.Schedule;
import com.example.student.service.ScheduleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoaderSchedule implements CommandLineRunner {

    private final ScheduleService scheduleService;

    public DataLoaderSchedule(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Override
    public void run(String... args) throws Exception {
        Schedule mikrokontroller = new Schedule();
        mikrokontroller.setId("1");
        mikrokontroller.setTitle("Mikrokontroller dhe mikroprocesore");
        mikrokontroller.setDescription("Lënda trajton konceptet e mikrokontrollerëve dhe mikroprocesorëve.");
        mikrokontroller.setDuration(90);
        mikrokontroller.setUrlDocument("http://example.com/mikrokontroller.pdf");
        mikrokontroller.setStartTime(LocalDateTime.of(2025, 5, 26, 8, 0));
        mikrokontroller.setEndTime(LocalDateTime.of(2025, 5, 26, 9, 30));
        mikrokontroller.setSupervisor("Prof. Ali");
        mikrokontroller.setStatus("confirmed");
        scheduleService.createSchedule(mikrokontroller);

        Schedule rrjetet = new Schedule();
        rrjetet.setId("2");
        rrjetet.setTitle("Rrjetet kompjuterike");
        rrjetet.setDescription("Lënda mbulon bazat dhe teknologjitë e rrjeteve kompjuterike.");
        rrjetet.setDuration(90);
        rrjetet.setUrlDocument("http://example.com/rrjetet.pdf");
        rrjetet.setStartTime(LocalDateTime.of(2025, 5, 26, 10, 0));
        rrjetet.setEndTime(LocalDateTime.of(2025, 5, 26, 11, 30));
        rrjetet.setSupervisor("Prof. Blerim");
        rrjetet.setStatus("confirmed");
        scheduleService.createSchedule(rrjetet);

        Schedule ndermarresi = new Schedule();
        ndermarresi.setId("3");
        ndermarresi.setTitle("Ndërmarrësi dhe inovacion");
        ndermarresi.setDescription("Krijimi dhe menaxhimi i bizneseve inovative.");
        ndermarresi.setDuration(90);
        ndermarresi.setUrlDocument("http://example.com/ndermarresi.pdf");
        ndermarresi.setStartTime(LocalDateTime.of(2025, 5, 26, 12, 0));
        ndermarresi.setEndTime(LocalDateTime.of(2025, 5, 26, 13, 30));
        ndermarresi.setSupervisor("Prof. Drita");
        ndermarresi.setStatus("confirmed");
        scheduleService.createSchedule(ndermarresi);

        Schedule internetSecurity = new Schedule();
        internetSecurity.setId("4");
        internetSecurity.setTitle("Internet Security");
        internetSecurity.setDescription("Mbrojtja e sistemeve dhe rrjeteve në internet.");
        internetSecurity.setDuration(90);
        internetSecurity.setUrlDocument("http://example.com/internetsecurity.pdf");
        internetSecurity.setStartTime(LocalDateTime.of(2025, 5, 26, 14, 0));
        internetSecurity.setEndTime(LocalDateTime.of(2025, 5, 26, 15, 30));
        internetSecurity.setSupervisor("Prof. Erion");
        internetSecurity.setStatus("confirmed");
        scheduleService.createSchedule(internetSecurity);

        Schedule sistemeOperative = new Schedule();
        sistemeOperative.setId("5");
        sistemeOperative.setTitle("Sisteme operative");
        sistemeOperative.setDescription("Konceptet dhe arkitektura e sistemeve operative.");
        sistemeOperative.setDuration(90);
        sistemeOperative.setUrlDocument("http://example.com/sistemeoperative.pdf");
        sistemeOperative.setStartTime(LocalDateTime.of(2025, 5, 27, 8, 0));
        sistemeOperative.setEndTime(LocalDateTime.of(2025, 5, 27, 9, 30));
        sistemeOperative.setSupervisor("Prof. Ardita");
        sistemeOperative.setStatus("confirmed");
        scheduleService.createSchedule(sistemeOperative);

        Schedule dizajniAlgoritmeve = new Schedule();
        dizajniAlgoritmeve.setId("6");
        dizajniAlgoritmeve.setTitle("Dizajni dhe analiza e algoritmeve");
        dizajniAlgoritmeve.setDescription("Studimi i algoritmeve dhe efikasiteti i tyre.");
        dizajniAlgoritmeve.setDuration(90);
        dizajniAlgoritmeve.setUrlDocument("http://example.com/algoritme.pdf");
        dizajniAlgoritmeve.setStartTime(LocalDateTime.of(2025, 5, 27, 10, 0));
        dizajniAlgoritmeve.setEndTime(LocalDateTime.of(2025, 5, 27, 11, 30));
        dizajniAlgoritmeve.setSupervisor("Prof. Luljeta");
        dizajniAlgoritmeve.setStatus("confirmed");
        scheduleService.createSchedule(dizajniAlgoritmeve);

        Schedule programimMobile = new Schedule();
        programimMobile.setId("7");
        programimMobile.setTitle("Programim i pajisjeve mobile");
        programimMobile.setDescription("Zhvillimi i aplikacioneve për pajisje mobile.");
        programimMobile.setDuration(90);
        programimMobile.setUrlDocument("http://example.com/mobileprogramming.pdf");
        programimMobile.setStartTime(LocalDateTime.of(2025, 5, 27, 12, 0));
        programimMobile.setEndTime(LocalDateTime.of(2025, 5, 27, 13, 30));
        programimMobile.setSupervisor("Prof. Elira");
        programimMobile.setStatus("confirmed");
        scheduleService.createSchedule(programimMobile);

        Schedule testimiSoftuerit = new Schedule();
        testimiSoftuerit.setId("8");
        testimiSoftuerit.setTitle("Testimi i softuerit");
        testimiSoftuerit.setDescription("Metodat dhe praktikat për testimin e softuerit.");
        testimiSoftuerit.setDuration(90);
        testimiSoftuerit.setUrlDocument("http://example.com/testimi.pdf");
        testimiSoftuerit.setStartTime(LocalDateTime.of(2025, 5, 27, 14, 0));
        testimiSoftuerit.setEndTime(LocalDateTime.of(2025, 5, 27, 15, 30));
        testimiSoftuerit.setSupervisor("Prof. Mentor");
        testimiSoftuerit.setStatus("confirmed");
        scheduleService.createSchedule(testimiSoftuerit);
    }
}
