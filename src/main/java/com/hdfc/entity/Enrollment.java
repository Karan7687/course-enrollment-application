package com.hdfc.entity;

import java.time.LocalDate;

public class Enrollment {

    private Integer enrollmentId;
    private Integer employeeId;
    private String employeeName;
    private Integer courseId;
    private LocalDate enrollmentDate;
    private EnrollmentStatus status;

    public Enrollment() {
    }

    public Enrollment(Integer enrollmentId, Integer employeeId, String employeeName, Integer courseId,
                      LocalDate enrollmentDate, EnrollmentStatus status) {
        this.enrollmentId = enrollmentId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    public Integer getEnrollmentId() { return enrollmentId; }
    public void setEnrollmentId(Integer enrollmentId) { this.enrollmentId = enrollmentId; }
    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }
    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    public Integer getCourseId() { return courseId; }
    public void setCourseId(Integer courseId) { this.courseId = courseId; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }
    public EnrollmentStatus getStatus() { return status; }
    public void setStatus(EnrollmentStatus status) { this.status = status; }
}
