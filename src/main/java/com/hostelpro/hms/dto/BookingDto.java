package com.hostelpro.hms.dto;

import com.hostelpro.hms.entities.Enum.BookingStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.hostelpro.hms.entities.Booking}
 */
public record BookingDto(Long id, StudentDetailsDto1 studentDetails, RoomDto1 room, BookingStatus status,
                         @NotNull LocalDate requestDate, LocalDate approvalDate) implements Serializable {
    /**
     * DTO for {@link com.hostelpro.hms.entities.StudentDetails}
     */
    public record StudentDetailsDto1(@NotBlank String studentName) implements Serializable {
    }

    /**
     * DTO for {@link com.hostelpro.hms.entities.Room}
     */
    public record RoomDto1(@NotBlank String roomNumber) implements Serializable {
    }
}