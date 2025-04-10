package com.hostelpro.hms.Repositories;

import com.hostelpro.hms.Entities.Booking;
import com.hostelpro.hms.Entities.Enum.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {
    Booking findBookingsById(Long id);
    Booking findByStudentDetails_Id(Long studentDetailsId);
    Booking findByRoom_Id(Long roomId);
    List<Booking> findBookingsByStatus(BookingStatus status);
//    Booking findBookingsByDa(String bookingDate);
//    Booking findByCheckInDate(String checkInDate);
//    Booking findByCheckOutDate(String checkOutDate);
    Booking findByStudentDetails_IdAndActiveTrue(Long studentId);
}