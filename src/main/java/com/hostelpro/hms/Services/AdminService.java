package com.hostelpro.hms.services;

import com.hostelpro.hms.dto.HostelDto;
import com.hostelpro.hms.dto.Requests.RegisterRequest;
import com.hostelpro.hms.dto.RoomDto;
import com.hostelpro.hms.dto.WardenDetailsDto;

import java.util.List;

public interface AdminService {
    void addHostel(HostelDto hostelDto);
    void addWarden(RegisterRequest registerRequest);
    List<WardenDetailsDto> getAllWardens();
    void assignHostelToWarden(Long wardenId, Long hostelId);

}
