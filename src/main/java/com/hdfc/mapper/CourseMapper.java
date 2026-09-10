package com.hdfc.mapper;

import com.hdfc.dto.CourseRequestDto;
import com.hdfc.dto.CourseResponseDto;
import com.hdfc.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequestDto request, Integer courseId) {
        return new Course(courseId, request.getCourseName(), request.getTrainerName(),
                request.getDurationInDays(), request.getMaxCapacity(), request.getFees());
    }

    public CourseResponseDto toResponse(Course course) {
        return new CourseResponseDto(course.getCourseId(), course.getCourseName(), course.getTrainerName(),
                course.getDurationInDays(), course.getMaxCapacity(), course.getFees());
    }
}
