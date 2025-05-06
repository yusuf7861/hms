package com.hostelpro.hms.services;

import com.hostelpro.hms.dto.*;

import java.util.List;

public interface WardenService {
    void saveWardenDetails(Long UserId, CreateWardenDto dto);
    WardenProfileDto getWardenDetails(Long userId);
    List<RoomDto> getRooms(Long wardenId);
    void updateRoomAvailability(Long roomId, boolean available);
    List<StudentSummaryDto> getHostelStudents(Long wardenId);
    StudentDetailsDto getStudentDetailsById(Long studentId, Long wardenId);
    List<BookingDto> getPendingBookings(Long wardenId);
    void approveBooking(Long bookingId);
    void rejectBooking(Long bookingId);
    List<PaymentDto> getAllPayments(Long wardenId);
    void addRoom(RoomDto room, Long wardenId);
}
