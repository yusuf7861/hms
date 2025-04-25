package com.hostelpro.hms.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.hostelpro.hms.entities.Hostel}
 */
public record HostelDtoInfo(@NotBlank String name, @NotBlank(message = "Location is required") String location,
                            String contactNumber, List<RoomDto1> rooms) implements Serializable {
    /**
     * DTO for {@link com.hostelpro.hms.entities.Room}
     */
    public record RoomDto1(Long id, @NotBlank String roomNumber) implements Serializable {
    }
}