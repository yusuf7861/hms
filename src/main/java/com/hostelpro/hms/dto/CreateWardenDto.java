package com.hostelpro.hms.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.hostelpro.hms.entities.WardenDetails}
 */
public record CreateWardenDto(@NotBlank String name, String contactNumber, String address) implements Serializable {
}