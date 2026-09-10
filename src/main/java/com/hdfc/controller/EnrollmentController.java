package com.hdfc.controller;

import com.hdfc.dto.EnrollmentRequestDto;
import com.hdfc.dto.EnrollmentResponseDto;
import com.hdfc.entity.EnrollmentStatus;
import com.hdfc.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponseDto> enroll(@Valid @RequestBody EnrollmentRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.enroll(request));
    }

    @GetMapping
    public List<EnrollmentResponseDto> getAll() { return enrollmentService.getAll(); }

    @GetMapping("/{id}")
    public EnrollmentResponseDto getById(@PathVariable Integer id) { return enrollmentService.getById(id); }

    @PutMapping("/{id}/cancel")
    public EnrollmentResponseDto cancel(@PathVariable Integer id) { return enrollmentService.cancel(id); }

    @PutMapping("/{id}/complete")
    public EnrollmentResponseDto complete(@PathVariable Integer id) { return enrollmentService.complete(id); }

    @GetMapping("/status/{status}")
    public List<EnrollmentResponseDto> findByStatus(@PathVariable EnrollmentStatus status) {
        return enrollmentService.findByStatus(status);
    }

    @GetMapping("/employee/{employeeId}")
    public List<EnrollmentResponseDto> findByEmployeeId(@PathVariable Integer employeeId) {
        return enrollmentService.findByEmployeeId(employeeId);
    }
}
