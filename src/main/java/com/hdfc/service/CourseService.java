package com.hdfc.service;

import com.hdfc.dto.CourseRequestDto;
import com.hdfc.dto.CourseResponseDto;

import java.util.List;

public interface CourseService {
    CourseResponseDto create(CourseRequestDto request);
    CourseResponseDto getById(Integer id);
    List<CourseResponseDto> getAll();
    CourseResponseDto update(Integer id, CourseRequestDto request);
    void delete(Integer id);
    List<CourseResponseDto> findByTrainer(String trainerName);
    List<CourseResponseDto> findByFeesLessThan(Double amount);
    long count();
}
