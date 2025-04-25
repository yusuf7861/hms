package com.hostelpro.hms.dto;

import com.hostelpro.hms.entities.Hostel;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link Hostel}
 */
public record HostelDto(@NotBlank String name, @NotBlank(message = "Location is required") String location,
                        String contactNumber) implements Serializable {
}