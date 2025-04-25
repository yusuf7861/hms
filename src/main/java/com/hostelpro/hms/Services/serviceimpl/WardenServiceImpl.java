package com.hostelpro.hms.services.serviceimpl;

import com.hostelpro.hms.dto.WardenDetailsDto;
import com.hostelpro.hms.entities.Hostel;
import com.hostelpro.hms.entities.User;
import com.hostelpro.hms.entities.WardenDetails;
import com.hostelpro.hms.exceptions.ResourceNotFoundException;
import com.hostelpro.hms.repositories.HostelRepository;
import com.hostelpro.hms.repositories.UserRepository;
import com.hostelpro.hms.repositories.WardenDetailsRepository;
import com.hostelpro.hms.services.WardenService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WardenServiceImpl implements WardenService {

    private final UserRepository userRepository;
    private final HostelRepository hostelRepository;
    private final WardenDetailsRepository wardenDetailsRepository;

    public WardenServiceImpl(UserRepository userRepository, HostelRepository hostelRepository, WardenDetailsRepository wardenDetailsRepository) {
        this.userRepository = userRepository;
        this.hostelRepository = hostelRepository;
        this.wardenDetailsRepository = wardenDetailsRepository;
    }

    @Override
    @PreAuthorize("hasRole('WARDEN')")
    @Transactional
    public void saveWardenDetails(Long userId, Long hostelId, WardenDetailsDto wardenDetailsDto) {
        try {

            User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));

            Hostel hostel = hostelRepository.findById(hostelId).orElseThrow(() -> new ResourceNotFoundException("Hostel not found"));

            WardenDetails warden = new WardenDetails();
            warden.setUser(user);
            warden.setName(wardenDetailsDto.name());
            warden.setContactNumber(wardenDetailsDto.contactNumber());
            warden.setAddress(wardenDetailsDto.address());
            warden.setHostel(hostel);

            wardenDetailsRepository.save(warden);
        } catch (UsernameNotFoundException | ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
