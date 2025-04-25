package com.hostelpro.hms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

/**
 * DTO for {@link com.hostelpro.hms.entities.StudentDetails}
 */
public record UpdateStudentDetailsDto(@NotBlank String guardianContactNumber, @Pattern(regexp = "\\d{10}") String phone, String address) implements Serializable {
}