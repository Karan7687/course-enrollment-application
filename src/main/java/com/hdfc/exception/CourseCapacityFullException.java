package com.hdfc.exception;

public class CourseCapacityFullException extends RuntimeException {
    public CourseCapacityFullException(Integer courseId) { super("Course capacity is full: " + courseId); }
}
