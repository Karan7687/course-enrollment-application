package com.hdfc.service;

import com.hdfc.dto.CourseRequestDto;
import com.hdfc.dto.CourseResponseDto;
import com.hdfc.entity.Course;
import com.hdfc.exception.CourseNotFoundException;
import com.hdfc.mapper.CourseMapper;
import com.hdfc.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public CourseResponseDto create(CourseRequestDto request) {
        return courseMapper.toResponse(courseRepository.save(courseMapper.toEntity(request, null)));
    }

    @Override
    public CourseResponseDto getById(Integer id) {
        return courseMapper.toResponse(findCourse(id));
    }

    @Override
    public List<CourseResponseDto> getAll() {
        return courseRepository.findAll().stream().map(courseMapper::toResponse).toList();
    }

    @Override
    public CourseResponseDto update(Integer id, CourseRequestDto request) {
        findCourse(id);
        return courseMapper.toResponse(courseRepository.save(courseMapper.toEntity(request, id)));
    }

    @Override
    public void delete(Integer id) {
        findCourse(id);
        courseRepository.deleteById(id);
    }

    @Override
    public List<CourseResponseDto> findByTrainer(String trainerName) {
        return courseRepository.findAll().stream()
                .filter(course -> course.getTrainerName().equalsIgnoreCase(trainerName))
                .map(courseMapper::toResponse)
                .toList();
    }

    @Override
    public List<CourseResponseDto> findByFeesLessThan(Double amount) {
        return courseRepository.findAll().stream()
                .filter(course -> course.getFees() < amount)
                .map(courseMapper::toResponse)
                .toList();
    }

    @Override
    public long count() { return courseRepository.findAll().stream().count(); }

    private Course findCourse(Integer id) {
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
    }
}
