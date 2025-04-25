package com.hostelpro.hms.services;

import com.hostelpro.hms.dto.HostelDtoInfo;

import java.util.List;

public interface HostelService {
    List<HostelDtoInfo> getAllHostels();
}
