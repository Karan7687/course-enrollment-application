package com.hdfc.repository;

import com.hdfc.entity.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class EnrollmentRepository {

    private final Map<Integer, Enrollment> enrollments = new ConcurrentHashMap<>();
    private final AtomicInteger sequence = new AtomicInteger();

    public Enrollment save(Enrollment enrollment) {
        if (enrollment.getEnrollmentId() == null) {
            enrollment.setEnrollmentId(sequence.incrementAndGet());
        }
        enrollments.put(enrollment.getEnrollmentId(), enrollment);
        return enrollment;
    }

    public Optional<Enrollment> findById(Integer id) { return Optional.ofNullable(enrollments.get(id)); }
    public List<Enrollment> findAll() { return new ArrayList<>(enrollments.values()); }
    public void deleteById(Integer id) { enrollments.remove(id); }
}
