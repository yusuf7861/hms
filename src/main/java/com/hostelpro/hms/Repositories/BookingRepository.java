package com.hostelpro.hms.repositories;

import com.hostelpro.hms.entities.Booking;
import com.hostelpro.hms.entities.Enum.BookingStatus;
import com.hostelpro.hms.mapper.BookingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT b FROM Booking b WHERE b.studentDetails.id = :studentId")
    BookingInfo findBookingByStudentId(@Param("studentId") Long studentId);
}