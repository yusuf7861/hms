package com.hostelpro.hms.controllers;


import com.hostelpro.hms.dto.HostelDto;
import com.hostelpro.hms.dto.Requests.RegisterRequest;
import com.hostelpro.hms.services.AdminService;
import com.hostelpro.hms.services.WardenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final WardenService wardenService;


    @PostMapping("/hostel/add")
    public ResponseEntity<?> addHostel(HostelDto dto)
    {
        try {
            adminService.addHostel(dto);
            return ResponseEntity.ok("Hostel added successfully");
        } catch (Exception e)
        {
            return ResponseEntity.status(500).body("Failed to add hostel: " + e.getMessage());
        }
    }

    @PostMapping("/wardens/add")
    public ResponseEntity<?>  addWarden(RegisterRequest registerRequest)
    {
        adminService.addWarden(registerRequest);
        return ResponseEntity.ok("Warden Registered successfully.");
    }

}
