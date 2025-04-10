package com.hostelpro.hms.Controllers;

import com.hostelpro.hms.DTOs.HostelDto;
import com.hostelpro.hms.DTOs.Requests.RegisterRequest;
import com.hostelpro.hms.Entities.Hostel;
import com.hostelpro.hms.Repositories.HostelRepository;
import com.hostelpro.hms.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/rooms/add")
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
