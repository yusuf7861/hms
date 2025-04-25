package com.hostelpro.hms.controllers;

import com.hostelpro.hms.configurations.CustomUserDetails;
import com.hostelpro.hms.dto.WardenDetailsDto;
import com.hostelpro.hms.services.StudentService;
import com.hostelpro.hms.services.WardenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/warden")
@RequiredArgsConstructor
public class WardenController {

    private final StudentService studentService;
    private final WardenService wardenService;

    @GetMapping("/students")
    public ResponseEntity<?> getStudents()
    {
        return ResponseEntity.ok(studentService.getAllStudentDetails());
    }

    @PostMapping("/save-details")
    public ResponseEntity<?> saveWardenDetails(@AuthenticationPrincipal CustomUserDetails userDetails, Long hostelId, WardenDetailsDto wardenDetailsDto)
    {
        try {
            wardenService.saveWardenDetails(userDetails.getId(), hostelId, wardenDetailsDto);
            return ResponseEntity.ok("Warden details saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to save warden details: " + e.getMessage());
        }
    }
}
