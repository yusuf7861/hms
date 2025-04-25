package com.hostelpro.hms.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.hostelpro.hms.entities.WardenDetails}
 */
public record WardenProfileDto(Long id, @NotBlank String name, String contactNumber, String address,
                               HostelDto hostel) implements Serializable {
    /**
     * DTO for {@link com.hostelpro.hms.entities.Hostel}
     */
    public record HostelDto(Long id, @NotBlank String name,
                             @NotBlank(message = "Location is required") String location,
                             String contactNumber) implements Serializable {
    }
}