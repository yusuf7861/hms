package com.hostelpro.hms.dto.Requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.hostelpro.hms.entities.User}
 */
public record LoginRequest(@Email @NotBlank String email,
                           @Size(min = 6) @NotBlank String password) implements Serializable {
}