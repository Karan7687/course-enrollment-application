package com.hdfc.service;

import com.hdfc.dto.EnrollmentRequestDto;
import com.hdfc.dto.EnrollmentResponseDto;
import com.hdfc.dto.CourseResponseDto;
import com.hdfc.entity.EnrollmentStatus;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponseDto enroll(EnrollmentRequestDto request);
    List<EnrollmentResponseDto> getAll();
    EnrollmentResponseDto getById(Integer id);
    EnrollmentResponseDto cancel(Integer id);
    EnrollmentResponseDto complete(Integer id);
    List<EnrollmentResponseDto> findByStatus(EnrollmentStatus status);
    List<EnrollmentResponseDto> findByEmployeeId(Integer employeeId);
    long count();
    CourseResponseDto mostPopularCourse();
}
