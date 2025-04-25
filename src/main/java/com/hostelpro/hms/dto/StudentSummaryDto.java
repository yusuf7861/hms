package com.hostelpro.hms.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.hostelpro.hms.entities.StudentDetails}
 */
public record StudentSummaryDto(Long id,
                                @NotBlank String name,
                                @NotBlank String gender,
                                String phone,
                                String department,
                                HostelDto hostel
) implements Serializable {
    public record HostelDto(Long id, @NotBlank String name) implements Serializable {}
}