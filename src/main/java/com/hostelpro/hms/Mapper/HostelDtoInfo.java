package com.hostelpro.hms.mapper;

import com.hostelpro.hms.dto.HostelDto;

/**
 * Projection for {@link HostelDto}
 */
public interface HostelDtoInfo {
    Long getId();

    String getName();

    String getLocation();

    String getContactNumber();
}