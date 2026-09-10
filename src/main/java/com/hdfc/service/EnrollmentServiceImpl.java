package com.hdfc.service;

import com.hdfc.dto.EnrollmentRequestDto;
import com.hdfc.dto.EnrollmentResponseDto;
import com.hdfc.dto.CourseResponseDto;
import com.hdfc.entity.Course;
import com.hdfc.entity.Enrollment;
import com.hdfc.entity.EnrollmentStatus;
import com.hdfc.exception.CourseCapacityFullException;
import com.hdfc.exception.CourseNotFoundException;
import com.hdfc.exception.DuplicateEnrollmentException;
import com.hdfc.exception.EnrollmentNotFoundException;
import com.hdfc.mapper.EnrollmentMapper;
import com.hdfc.mapper.CourseMapper;
import com.hdfc.repository.CourseRepository;
import com.hdfc.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final CourseMapper courseMapper;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository,
                                 EnrollmentMapper enrollmentMapper, CourseMapper courseMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentMapper = enrollmentMapper;
        this.courseMapper = courseMapper;
    }

    @Override
    public EnrollmentResponseDto enroll(EnrollmentRequestDto request) {
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException(request.getCourseId()));

        boolean duplicate = enrollmentRepository.findAll().stream()
                .anyMatch(enrollment -> enrollment.getEmployeeId().equals(request.getEmployeeId())
                        && enrollment.getCourseId().equals(request.getCourseId())
                        && enrollment.getStatus() == EnrollmentStatus.ENROLLED);
        if (duplicate) {
            throw new DuplicateEnrollmentException(request.getEmployeeId(), request.getCourseId());
        }

        long enrolledCount = enrollmentRepository.findAll().stream()
                .filter(enrollment -> enrollment.getCourseId().equals(course.getCourseId()))
                .filter(enrollment -> enrollment.getStatus() == EnrollmentStatus.ENROLLED)
                .count();
        if (enrolledCount >= course.getMaxCapacity()) {
            throw new CourseCapacityFullException(course.getCourseId());
        }

        Enrollment enrollment = new Enrollment(null, request.getEmployeeId(), request.getEmployeeName(),
                request.getCourseId(), LocalDate.now(), EnrollmentStatus.ENROLLED);
        return enrollmentMapper.toResponse(enrollmentRepository.save(enrollment));
    }

    @Override
    public List<EnrollmentResponseDto> getAll() {
        return enrollmentRepository.findAll().stream().map(enrollmentMapper::toResponse).toList();
    }

    @Override
    public EnrollmentResponseDto getById(Integer id) {
        return enrollmentMapper.toResponse(findEnrollment(id));
    }

    @Override
    public EnrollmentResponseDto cancel(Integer id) {
        Enrollment enrollment = findEnrollment(id);
        enrollment.setStatus(EnrollmentStatus.CANCELLED);
        return enrollmentMapper.toResponse(enrollmentRepository.save(enrollment));
    }

    @Override
    public EnrollmentResponseDto complete(Integer id) {
        Enrollment enrollment = findEnrollment(id);
        enrollment.setStatus(EnrollmentStatus.COMPLETED);
        return enrollmentMapper.toResponse(enrollmentRepository.save(enrollment));
    }

    @Override
    public List<EnrollmentResponseDto> findByStatus(EnrollmentStatus status) {
        return enrollmentRepository.findAll().stream()
                .filter(enrollment -> enrollment.getStatus() == status)
                .map(enrollmentMapper::toResponse).toList();
    }

    @Override
    public List<EnrollmentResponseDto> findByEmployeeId(Integer employeeId) {
        return enrollmentRepository.findAll().stream()
                .filter(enrollment -> enrollment.getEmployeeId().equals(employeeId))
                .map(enrollmentMapper::toResponse).toList();
    }

    @Override
    public long count() { return enrollmentRepository.findAll().stream().count(); }

    @Override
    public CourseResponseDto mostPopularCourse() {
        Map<Integer, Long> counts = enrollmentRepository.findAll().stream()
                .collect(Collectors.groupingBy(Enrollment::getCourseId, Collectors.counting()));
        return counts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .flatMap(entry -> courseRepository.findById(entry.getKey()))
                .map(courseMapper::toResponse)
                .orElse(null);
    }

    private Enrollment findEnrollment(Integer id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException(id));
    }
}
