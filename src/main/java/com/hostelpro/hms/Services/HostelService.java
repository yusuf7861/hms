package com.hostelpro.hms.services;

import com.hostelpro.hms.mapper.HostelDtoInfo;

import java.util.List;

public interface HostelService {
    List<HostelDtoInfo> getAllHostels();
}
