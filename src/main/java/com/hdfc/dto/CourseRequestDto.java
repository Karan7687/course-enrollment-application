package com.hdfc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CourseRequestDto {

    @NotBlank
    private String courseName;
    @NotBlank
    private String trainerName;
    @NotNull
    @Positive
    private Integer durationInDays;
    @NotNull
    @Positive
    private Integer maxCapacity;
    @NotNull
    @Positive
    private Double fees;

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getTrainerName() { return trainerName; }
    public void setTrainerName(String trainerName) { this.trainerName = trainerName; }
    public Integer getDurationInDays() { return durationInDays; }
    public void setDurationInDays(Integer durationInDays) { this.durationInDays = durationInDays; }
    public Integer getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(Integer maxCapacity) { this.maxCapacity = maxCapacity; }
    public Double getFees() { return fees; }
    public void setFees(Double fees) { this.fees = fees; }
}
