package com.hdfc.dto;

public record CourseResponseDto(Integer courseId, String courseName, String trainerName,
                                Integer durationInDays, Integer maxCapacity, Double fees) {
}
