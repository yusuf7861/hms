package com.hostelpro.hms.controllers;

import com.hostelpro.hms.dto.HostelDtoInfo;
import com.hostelpro.hms.services.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/public")
public class PublicControllers {
    @Autowired
    private HostelService hostelService;

    @GetMapping("/hostels")
    public ResponseEntity<List<HostelDtoInfo>> getAllHostels() {
        return ResponseEntity.ok(hostelService.getAllHostels());
    }
}
