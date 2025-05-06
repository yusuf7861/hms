package com.hostelpro.hms.controllers;


import com.hostelpro.hms.configurations.CustomUserDetails;
import com.hostelpro.hms.dto.*;
import com.hostelpro.hms.dto.Requests.RegisterRequest;
import com.hostelpro.hms.services.AdminService;
import com.hostelpro.hms.services.WardenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final WardenService wardenService;

    private final AdminService adminService;


    @PostMapping("/hostel/add")
    public ResponseEntity<?> addHostel(@RequestBody HostelDto dto)
    {
        try {
            adminService.addHostel(dto);
            return ResponseEntity.ok("Hostel added successfully");
        } catch (Exception e)
        {
            return ResponseEntity.status(500).body("Failed to add hostel: " + e.getMessage());
        }
    }

    @PostMapping("/warden/register")
    public ResponseEntity<?>  addWarden(@RequestBody RegisterRequest registerRequest)
    {
        adminService.addWarden(registerRequest);
        return ResponseEntity.ok("Warden Registered successfully.");
    }

    @GetMapping("/wardens")
    public ResponseEntity<List<WardenDetailsDto>> getAllWardens() {
        try {
            List<WardenDetailsDto> wardens = adminService.getAllWardens();
            return ResponseEntity.ok(wardens);
        } catch (Exception e)
        {
            return ResponseEntity.status(500).body(null);
        }
    }
}
