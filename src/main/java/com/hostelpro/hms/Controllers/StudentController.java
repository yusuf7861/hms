package com.hostelpro.hms.controllers;

import com.hostelpro.hms.configurations.CustomUserDetails;
import com.hostelpro.hms.dto.PaymentDto;
import com.hostelpro.hms.dto.StudentDetailsDto;
import com.hostelpro.hms.dto.UpdateStudentDetailsDto;
import com.hostelpro.hms.entities.Booking;
import com.hostelpro.hms.exceptions.ResourceNotFoundException;
import com.hostelpro.hms.mapper.BookingInfo;
import com.hostelpro.hms.mapper.StudentDetailsInfo;
import com.hostelpro.hms.repositories.BookingRepository;
import com.hostelpro.hms.services.PaymentService;
import com.hostelpro.hms.services.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final BookingRepository bookingRepository;
    private final PaymentService paymentService;

    //    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/register")
    public ResponseEntity<String> addStudentDetails(@RequestBody StudentDetailsDto studentDto, @AuthenticationPrincipal CustomUserDetails loggedInUser)
    {
        studentService.addStudentDetails(studentDto, loggedInUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body("Student details added successfully");
    }

//    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents()
    {
        List<StudentDetailsInfo> studentDetails = studentService.getAllStudentDetails();
        if (studentDetails.isEmpty())
            return new ResponseEntity<>("No students found", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(studentDetails);
    }

//    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal CustomUserDetails loggedInUser) {
        try {
            StudentDetailsDto detailsDto = studentService.getStudentDetailsById(loggedInUser.getId());
            return ResponseEntity.ok(detailsDto);
        } catch (Exception e){
            throw new ResourceNotFoundException("Student details not found for user ID: " + loggedInUser.getId());
        }
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PutMapping("/update")
    public ResponseEntity<?> updateStudentDetails(@RequestBody UpdateStudentDetailsDto dto, @AuthenticationPrincipal CustomUserDetails loggedInUser) {
        studentService.updateStudentDetails(loggedInUser.getId(), dto);
        return ResponseEntity.ok("Student details updated successfully");
    }

    @PostMapping("/book")
    public ResponseEntity<?> addBooking(@RequestParam Long roomId, @AuthenticationPrincipal CustomUserDetails userDetails){
        if (userDetails == null || userDetails.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not authenticated");
        }

        System.out.println("Authenticated User ID: " + userDetails.getId());
        try {
            studentService.addBooking(roomId, userDetails.getId());
            return ResponseEntity.ok("Booking added successfully");
        } catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add booking: " + e.getMessage());
        }
    }

    //TODO
    @GetMapping("/bookings")
    public ResponseEntity<?> getBooking(@AuthenticationPrincipal CustomUserDetails userDetails){
        try {
            BookingInfo booking = bookingRepository.findBookingByStudentId(userDetails.getId());

            if(booking == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No booking found");
            }
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            e.printStackTrace();    // console me errors dekhne k liye
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get booking: " + e.getMessage());
        }
    }

    @PostMapping("/pay")
    public ResponseEntity<?> doPayment(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody PaymentDto paymentDto) {
        try {
            return ResponseEntity.ok(studentService.doPayment(userDetails.getId(), paymentDto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process payment: " + e.getMessage());
        }
    }

    @GetMapping("/payments/history")
    public ResponseEntity<?> getPaymentHistory(@AuthenticationPrincipal CustomUserDetails userDetails)
    {
        try {
            List<PaymentDto> payments = paymentService.getAllPaymentsByStudentId(userDetails.getId());

            if (payments.isEmpty()) {
                return ResponseEntity.ok("No payments found");
            }

            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get payment history: " + e.getMessage());
        }
    }
}
