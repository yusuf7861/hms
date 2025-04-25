package com.hostelpro.hms.services;

import com.hostelpro.hms.dto.HostelDto;
import com.hostelpro.hms.dto.Requests.RegisterRequest;

public interface AdminService {
    void addHostel(HostelDto hostelDto);
    void addWarden(RegisterRequest registerRequest);
//    void addRoom(RoomDto roomDto, Long hostelId);
}
