package com.hdfc.repository;

import com.hdfc.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CourseRepository {

    private final Map<Integer, Course> courses = new ConcurrentHashMap<>();
    private final AtomicInteger sequence = new AtomicInteger();

    public Course save(Course course) {
        if (course.getCourseId() == null) {
            course.setCourseId(sequence.incrementAndGet());
        }
        courses.put(course.getCourseId(), course);
        return course;
    }

    public Optional<Course> findById(Integer id) { return Optional.ofNullable(courses.get(id)); }
    public List<Course> findAll() { return new ArrayList<>(courses.values()); }
    public void deleteById(Integer id) { courses.remove(id); }
    public boolean existsById(Integer id) { return courses.containsKey(id); }
}
