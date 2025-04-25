package com.hostelpro.hms.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.hostelpro.hms.entities.WardenDetails}
 */
public record WardenDetailsDto(@NotBlank String name, String contactNumber, String address, HostelDto1 hostel) implements Serializable {
    /**
     * DTO for {@link com.hostelpro.hms.entities.Hostel}
     */
    public record HostelDto1(Long id, @NotBlank String name) implements Serializable {
    }
}