package com.hostelpro.hms.services.serviceimpl;

import com.hostelpro.hms.dto.HostelDto;
import com.hostelpro.hms.dto.Requests.RegisterRequest;
import com.hostelpro.hms.dto.RoomDto;
import com.hostelpro.hms.dto.WardenDetailsDto;
import com.hostelpro.hms.entities.Enum.Role;
import com.hostelpro.hms.entities.Hostel;
import com.hostelpro.hms.entities.Room;
import com.hostelpro.hms.entities.User;
import com.hostelpro.hms.entities.WardenDetails;
import com.hostelpro.hms.exceptions.ResourceNotFoundException;
import com.hostelpro.hms.mapper.WardenDetailsMapper;
import com.hostelpro.hms.repositories.HostelRepository;
import com.hostelpro.hms.repositories.RoomRepository;
import com.hostelpro.hms.repositories.UserRepository;
import com.hostelpro.hms.repositories.WardenDetailsRepository;
import com.hostelpro.hms.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private HostelRepository hostelRepository;

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private WardenDetailsRepository wardenDetailsRepository;
    @Autowired
    private WardenDetailsMapper wardenDetailsMapper;


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

    @Override
    public List<WardenDetailsDto> getAllWardens() {
        List<WardenDetails> wardens = wardenDetailsRepository.findAll();
        return wardens.stream()
                .map(wardenDetailsMapper::toDto)
                .toList();
    }
}
