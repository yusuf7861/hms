package com.hostelpro.hms.mapper;

import com.hostelpro.hms.entities.Booking;
import com.hostelpro.hms.entities.Enum.BookingStatus;
import com.hostelpro.hms.entities.Room;

import java.time.LocalDate;

/**
 * Projection for {@link Booking}
 */
public interface BookingInfo {
    Long getId();

    BookingStatus getStatus();

    LocalDate getRequestDate();

    RoomInfo getRoom();

    /**
     * Projection for {@link Room}
     */
    interface RoomInfo {
        String getRoomNumber();
    }
}