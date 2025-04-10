package com.hostelpro.hms.Services;

import com.hostelpro.hms.DTOs.WardenDetailsDto;

public interface WardenService {
    WardenDetailsDto saveWardenDetails(Long UserId, Long hostelId, WardenDetailsDto wardenDetailsDto);
}
