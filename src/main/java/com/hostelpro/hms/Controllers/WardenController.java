package com.hostelpro.hms.controllers;

import com.hostelpro.hms.configurations.CustomUserDetails;
import com.hostelpro.hms.dto.*;
import com.hostelpro.hms.entities.Enum.BookingStatus;
import com.hostelpro.hms.entities.StudentDetails;
import com.hostelpro.hms.entities.WardenDetails;
import com.hostelpro.hms.repositories.StudentDetailsRepository;
import com.hostelpro.hms.repositories.WardenDetailsRepository;
import com.hostelpro.hms.services.StudentService;
import com.hostelpro.hms.services.WardenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/warden")
@RequiredArgsConstructor
public class WardenController {

    private final StudentService studentService;
    private final WardenService wardenService;
    private final WardenDetailsRepository wardenDetailsRepository;
    private final StudentDetailsRepository studentDetailsRepository;

    //TODO: only get the students that is come under the warden and student and details should be in the same hostel
//    @PreAuthorize("hasRole('WARDEN')")
//    @GetMapping("/students")
//    public ResponseEntity<?> getAllStudents()
//    {
//        List<StudentDetailsInfo> studentDetails = studentService.getAllStudentDetails();
//        if (studentDetails.isEmpty())
//            return new ResponseEntity<>("No students found", HttpStatus.NOT_FOUND);
//        return ResponseEntity.ok(studentDetails);
//    }

    @PostMapping("/save-details")
    public ResponseEntity<?> saveWardenDetails(@AuthenticationPrincipal CustomUserDetails userDetails, CreateWardenDto createWardenDto)
    {
        try {
            wardenService.saveWardenDetails(userDetails.getId(), createWardenDto);
            return ResponseEntity.ok("Warden details saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to save warden details: " + e.getMessage());
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> wardenProfile(@AuthenticationPrincipal CustomUserDetails userDetails){
        try {
            WardenProfileDto wardenDetails = wardenService.getWardenDetails(userDetails.getId());
            System.out.println(userDetails.getId());
            return ResponseEntity.ok(wardenDetails);
        } catch (Exception e) {
//            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to fetch warden details: " + e.getMessage());
        }
    }

    @PostMapping("/room/add")
    public ResponseEntity<?> addRoom(RoomDto dto, @AuthenticationPrincipal CustomUserDetails userDetails)
    {
        try {
            wardenService.addRoom(dto, userDetails.getId());
            return ResponseEntity.ok(new ApiResponse(true, "Room added successfully"));
        } catch (Exception e)
        {
            return ResponseEntity.status(500).body("Failed to add room: " + e.getMessage());
        }
    }

    @GetMapping("/rooms")
    public ResponseEntity<?> getRooms(@AuthenticationPrincipal CustomUserDetails userDetails){
        try {
            if (userDetails == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UserDetails is null. You may not be authenticated.");
            }

            Long wardenId = userDetails.getId();
            if (wardenId == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Warden ID is null");
            }

            List<RoomDto> rooms = wardenService.getRooms(wardenId);
            if (rooms.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
            }

            return ResponseEntity.ok(rooms);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error: " + e.getMessage());
        }
    }

    @PostMapping("/room/{roomId}/availability")
    public ResponseEntity<?> updateRoomAvailability(@PathVariable Long roomId, boolean available) {
        try {
            wardenService.updateRoomAvailability(roomId, available);
            return ResponseEntity.ok("Room availability updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to update room availability: " + e.getMessage());
        }
    }

    //TODO: iterating every students hostel and all the rooms
    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            List<StudentSummaryDto> hostelStudents = wardenService.getHostelStudents(userDetails.getId());
           return ResponseEntity.ok(hostelStudents);
        } catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch hostel students: " + e.getMessage());
        }
    }

    //TODO: make sure warden can only fetch student that belongs to their hostel only
//    @GetMapping("/student/{studentId}")
//    public ResponseEntity<?> getStudentById(@PathVariable Long studentId, @AuthenticationPrincipal CustomUserDetails userDetails) {
//        try {
//            Long wardenId = userDetails.getId();
//            StudentDetailsDto student = wardenService.getStudentDetailsById(studentId, wardenId);
//            if (student == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body("Student details not found");
//            }
//
//            // Check if the student's hostel matches the warden's hostel
//            if (!Objects.equals(student.getId(), wardenDetails.getHostel().getId())) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                        .body("You are not authorized to view this student's details");
//            }
//
//            // You can map student to a DTO if needed
//            return ResponseEntity.ok();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to fetch student details: " + e.getMessage());
//        }
//    }

    @GetMapping("/bookings/pending")
    public ResponseEntity<?> getPendingBookings(@AuthenticationPrincipal CustomUserDetails userDetails){
        try {
            List<BookingDto> pendingBookings = wardenService.getPendingBookings(userDetails.getId());
            if (pendingBookings.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No pending bookings found");
            }
            return ResponseEntity.ok(pendingBookings);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch pending bookings: " + e.getMessage());
        }
    }

    @PutMapping("/bookings/{bookingId}/approve")
    public ResponseEntity<?> approveBooking(@PathVariable Long bookingId) {
        try {
            wardenService.approveBooking(bookingId);
            return ResponseEntity.ok("Booking status updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update booking status: " + e.getMessage());
        }
    }

    @PutMapping("/bookings/{bookingId}/reject")
    public ResponseEntity<?> rejectBooking(@PathVariable Long bookingId) {
        try {
            wardenService.rejectBooking(bookingId);
            return ResponseEntity.ok("Booking status updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update booking status: " + e.getMessage());
        }
    }
}
