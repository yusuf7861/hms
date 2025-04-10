package com.hostelpro.hms.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

/**
 * DTO for {@link com.hostelpro.hms.Entities.StudentDetails}
 */
public record UpdateStudentDetailsDto(@NotBlank String guardianContactNumber, @Pattern(regexp = "\\d{10}") String phone,
                                 String department, String address,
                                 @NotBlank String collegeName) implements Serializable {
}