package com.hdfc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CourseEnrollmentApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void managesCoursesEnrollmentsAndAnalytics() throws Exception {
        mockMvc.perform(post("/courses")
                        .contentType("application/json")
                        .content("""
                                {
                                  "courseName": "Java",
                                  "trainerName": "Anita",
                                  "durationInDays": 5,
                                  "maxCapacity": 1,
                                  "fees": 1000
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.courseId").value(1));

        String enrollment = """
                {
                  "employeeId": 101,
                  "employeeName": "Ratan",
                  "courseId": 1
                }
                """;
        mockMvc.perform(post("/enrollments")
                        .contentType("application/json")
                        .content(enrollment))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("ENROLLED"));

        mockMvc.perform(post("/enrollments")
                        .contentType("application/json")
                        .content(enrollment))
                .andExpect(status().isConflict());

        mockMvc.perform(post("/enrollments")
                        .contentType("application/json")
                        .content("""
                                {
                                  "employeeId": 102,
                                  "employeeName": "Meera",
                                  "courseId": 1
                                }
                                """))
                .andExpect(status().isConflict());

        mockMvc.perform(get("/enrollments/status/ENROLLED"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employeeId").value(101));

        mockMvc.perform(get("/analytics/course-count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1));

        mockMvc.perform(get("/analytics/enrollment-count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1));

        mockMvc.perform(get("/analytics/most-popular-course"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseName").value("Java"));
    }
}
