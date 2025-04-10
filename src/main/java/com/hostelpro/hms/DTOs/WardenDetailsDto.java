package com.hostelpro.hms.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

/**
 * DTO for {@link com.hostelpro.hms.Entities.WardenDetails}
 */
public record WardenDetailsDto(@NotBlank String name, @Pattern(regexp = "\\d{10}") String contactNumber, String address) implements Serializable {
  }