package com.hostelpro.hms.services.serviceimpl;

import com.hostelpro.hms.exceptions.ResourceNotFoundException;
import com.hostelpro.hms.mapper.HostelDtoInfo;
import com.hostelpro.hms.repositories.HostelRepository;
import com.hostelpro.hms.services.HostelService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostelServiceImpl implements HostelService {

    @Autowired
    HostelRepository hostelRepository;

    @Override
//    @Transactional(readOnly = true) // learn about this
    public List<HostelDtoInfo> getAllHostels() {
        try {
            List<HostelDtoInfo> hostels = hostelRepository.findAllBy();

            if (hostels.isEmpty()) {
                throw new ResourceNotFoundException("No hostels found.");
            }
            return hostels;
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch hostel list", e);
        }
    }
}
