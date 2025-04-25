package com.hostelpro.hms.dto;

import com.hostelpro.hms.entities.Hostel;
import com.hostelpro.hms.entities.Room;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link Room}
 */
public record RoomDto(Long id, @NotBlank String roomNumber, @Min(1) @Max(2) int capacity, boolean available) implements Serializable {
}