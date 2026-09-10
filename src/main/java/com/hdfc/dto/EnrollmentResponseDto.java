package com.hdfc.dto;

import com.hdfc.entity.EnrollmentStatus;
import java.time.LocalDate;

public record EnrollmentResponseDto(Integer enrollmentId, Integer employeeId, String employeeName,
                                    Integer courseId, LocalDate enrollmentDate, EnrollmentStatus status) {
}
