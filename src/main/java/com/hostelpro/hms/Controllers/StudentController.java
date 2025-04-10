package com.hostelpro.hms.Controllers;

import com.hostelpro.hms.Configurations.CustomUserDetails;
import com.hostelpro.hms.DTOs.StudentDetailsDto;
import com.hostelpro.hms.DTOs.UpdateStudentDetailsDto;
import com.hostelpro.hms.Entities.StudentDetails;
import com.hostelpro.hms.Exceptions.ResourceNotFoundException;
import com.hostelpro.hms.Mapper.StudentDetailsInfo;
import com.hostelpro.hms.Repositories.StudentDetailsRepository;
import com.hostelpro.hms.Services.StudentDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.security.Principal;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentDetailsService studentDetailsService;

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/register")
    public ResponseEntity<String> addStudentDetails(@RequestBody StudentDetailsDto studentDto, @AuthenticationPrincipal CustomUserDetails loggedInUser)
    {
        studentDetailsService.addStudentDetails(studentDto, loggedInUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body("Student details added successfully");
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents()
    {
        List<StudentDetailsInfo> studentDetails = studentDetailsService.getAllStudentDetails();
        if (studentDetails.isEmpty())
            return new ResponseEntity<>("No students found", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(studentDetails);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal CustomUserDetails loggedInUser) {
        try {
            StudentDetailsDto detailsDto = studentDetailsService.getStudentDetailsById(loggedInUser.getId());
            return ResponseEntity.ok(detailsDto);
        } catch (Exception e){
            throw new ResourceNotFoundException("Student details not found for user ID: " + loggedInUser.getId());
        }
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PutMapping("/update")
    public ResponseEntity<?> updateStudentDetails(@RequestBody UpdateStudentDetailsDto dto, @AuthenticationPrincipal CustomUserDetails loggedInUser) {
        studentDetailsService.updateStudentDetails(loggedInUser.getId(), dto);
        return ResponseEntity.ok("Student details updated successfully");
    }
}
