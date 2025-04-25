package com.hostelpro.hms.services.serviceimpl;

import com.hostelpro.hms.dto.HostelDtoInfo;
import com.hostelpro.hms.entities.Hostel;
import com.hostelpro.hms.exceptions.ResourceNotFoundException;
import com.hostelpro.hms.mapper.HostelMapper;
import com.hostelpro.hms.repositories.HostelRepository;
import com.hostelpro.hms.services.HostelService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HostelServiceImpl implements HostelService {

    @Autowired
    private HostelRepository hostelRepository;

    @Autowired
    private HostelMapper hostelMapper;

    @Override
    @Transactional(readOnly = true) // learn about this
    public List<HostelDtoInfo> getAllHostels() {
        try {
            List<Hostel> hostels = hostelRepository.findAll();

            if (hostels.isEmpty()) {
                throw new ResourceNotFoundException("No hostels found.");
            }
            return hostels.stream()
                    .map(hostelMapper::toDto).toList();
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch hostel list", e);
        }
    }
}
