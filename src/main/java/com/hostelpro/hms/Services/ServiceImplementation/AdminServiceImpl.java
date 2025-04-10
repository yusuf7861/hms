package com.hostelpro.hms.Services.ServiceImplementation;

import com.hostelpro.hms.DTOs.HostelDto;
import com.hostelpro.hms.DTOs.Requests.RegisterRequest;
import com.hostelpro.hms.Entities.Enum.Role;
import com.hostelpro.hms.Entities.Hostel;
import com.hostelpro.hms.Entities.User;
import com.hostelpro.hms.Repositories.HostelRepository;
import com.hostelpro.hms.Repositories.UserRepository;
import com.hostelpro.hms.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private HostelRepository hostelRepository;

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addHostel(HostelDto hostelDto) {
        Hostel hostel = new Hostel();
        hostel.setName(hostelDto.name());
        hostel.setLocation(hostelDto.location());
        hostel.setContactNumber(hostelDto.contactNumber());
//        hostel.setWardens(hostelDto.warden());
        hostelRepository.save(hostel);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void addWarden(RegisterRequest registerRequest) {
        Optional<User> existingWarden = userRepository.findByEmail(registerRequest.email());
        if (existingWarden.isPresent()) {
            throw new RuntimeException("User already exists with this email.");
        }

        User user = new User();
        user.setEmail(registerRequest.email());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setRole(Role.WARDEN);
        userRepository.save(user);
    }

}
