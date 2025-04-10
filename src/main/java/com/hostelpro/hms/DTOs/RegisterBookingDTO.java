package com.hostelpro.hms.DTOs;

import com.hostelpro.hms.Entities.Enum.BookingStatus;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.hostelpro.hms.Entities.Booking}
 */
public record RegisterBookingDTO(@NotBlank String RoomNumber, BookingStatus status, LocalDate requestDate) implements Serializable {
}