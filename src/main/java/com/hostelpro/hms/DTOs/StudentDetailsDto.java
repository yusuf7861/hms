package com.hostelpro.hms.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

/**
 * DTO for {@link com.hostelpro.hms.Entities.StudentDetails}
 */
public record StudentDetailsDto(Long id, @NotBlank String name, @NotBlank String guardianName,
                                @NotBlank String guardianContactNumber, @NotBlank String gender,
                                @Pattern(regexp = "\\d{10}") String phone, @NotBlank String department,
                                @NotBlank String collegeName, String address) implements Serializable {
}