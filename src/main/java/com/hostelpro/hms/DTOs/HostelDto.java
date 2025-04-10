package com.hostelpro.hms.DTOs;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.hostelpro.hms.Entities.Hostel}
 */
public record HostelDto(@NotBlank String name, @NotBlank(message = "Location is required") String location,
                        @NotBlank(message = "Contact number is required") String contactNumber) implements Serializable {
}