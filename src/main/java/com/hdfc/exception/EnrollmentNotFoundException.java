package com.hdfc.exception;

public class EnrollmentNotFoundException extends RuntimeException {
    public EnrollmentNotFoundException(Integer enrollmentId) { super("Enrollment not found: " + enrollmentId); }
}
