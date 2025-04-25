package com.hostelpro.hms.services;

import com.hostelpro.hms.dto.WardenDetailsDto;

public interface WardenService {
    void saveWardenDetails(Long UserId, Long hostelId, WardenDetailsDto wardenDetailsDto);
}
