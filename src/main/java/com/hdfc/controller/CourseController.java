package com.hdfc.controller;

import com.hdfc.dto.CourseRequestDto;
import com.hdfc.dto.CourseResponseDto;
import com.hdfc.service.CourseService;
import com.hdfc.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class CourseController {

    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    public CourseController(CourseService courseService, EnrollmentService enrollmentService) {
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseResponseDto> create(@Valid @RequestBody CourseRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.create(request));
    }

    @GetMapping("/courses/{id}")
    public CourseResponseDto getById(@PathVariable Integer id) { return courseService.getById(id); }

    @GetMapping("/courses")
    public List<CourseResponseDto> getAll() { return courseService.getAll(); }

    @PutMapping("/courses/{id}")
    public CourseResponseDto update(@PathVariable Integer id, @Valid @RequestBody CourseRequestDto request) {
        return courseService.update(id, request);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/courses/trainer/{trainerName}")
    public List<CourseResponseDto> findByTrainer(@PathVariable String trainerName) {
        return courseService.findByTrainer(trainerName);
    }

    @GetMapping("/courses/fees/{amount}")
    public List<CourseResponseDto> findByFeesLessThan(@PathVariable Double amount) {
        return courseService.findByFeesLessThan(amount);
    }

    @GetMapping("/analytics/course-count")
    public long courseCount() { return courseService.count(); }

    @GetMapping("/analytics/enrollment-count")
    public long enrollmentCount() { return enrollmentService.count(); }

    @GetMapping("/analytics/most-popular-course")
    public CourseResponseDto mostPopularCourse() { return enrollmentService.mostPopularCourse(); }
}
