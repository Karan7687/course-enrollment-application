package com.hdfc.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Integer courseId) { super("Course not found: " + courseId); }
}
