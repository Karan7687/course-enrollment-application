package com.hdfc.exception;

public class DuplicateEnrollmentException extends RuntimeException {
    public DuplicateEnrollmentException(Integer employeeId, Integer courseId) {
        super("Employee " + employeeId + " is already enrolled in course " + courseId);
    }
}
