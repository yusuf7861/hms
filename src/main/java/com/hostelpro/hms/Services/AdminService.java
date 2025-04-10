package com.hostelpro.hms.Services;

import com.hostelpro.hms.DTOs.HostelDto;
import com.hostelpro.hms.DTOs.Requests.RegisterRequest;

public interface AdminService {
    void addHostel(HostelDto hostelDto);
    void addWarden(RegisterRequest registerRequest);
//    void addRoom(RoomDto roomDto, Long hostelId);
}
