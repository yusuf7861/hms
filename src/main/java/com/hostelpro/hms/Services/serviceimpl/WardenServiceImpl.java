package com.hostelpro.hms.services.serviceimpl;

import com.hostelpro.hms.dto.*;
import com.hostelpro.hms.entities.*;
import com.hostelpro.hms.entities.Enum.BookingStatus;
import com.hostelpro.hms.exceptions.ResourceNotFoundException;
import com.hostelpro.hms.mapper.BookingMapper;
import com.hostelpro.hms.mapper.RoomMapper;
import com.hostelpro.hms.mapper.StudentMapper;
import com.hostelpro.hms.mapper.WardenDetailsMapper;
import com.hostelpro.hms.repositories.*;
import com.hostelpro.hms.services.WardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WardenServiceImpl implements WardenService {

    private final UserRepository userRepository;
    private final HostelRepository hostelRepository;
    private final WardenDetailsRepository wardenDetailsRepository;
    private final WardenDetailsMapper wardenDetailsMapper;
    private final RoomRepository roomRepository;
    private final BookingMapper bookingMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentDetailsRepository studentDetailsRepository;
    @Autowired
    private BookingRepository bookingRepository;

    public WardenServiceImpl(UserRepository userRepository, HostelRepository hostelRepository, WardenDetailsRepository wardenDetailsRepository, WardenDetailsMapper wardenDetailsMapper, RoomRepository roomRepository, BookingMapper bookingMapper) {
        this.userRepository = userRepository;
        this.hostelRepository = hostelRepository;
        this.wardenDetailsRepository = wardenDetailsRepository;
        this.wardenDetailsMapper = wardenDetailsMapper;
        this.roomRepository = roomRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void saveWardenDetails(Long userId, CreateWardenDto createWardenDto) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            //TODO: hostel should be assigned by ADMIN, Warden cannot assign a hostel to himself.
//            Hostel hostel = hostelRepository.findById(hostelId).orElseThrow(() -> new ResourceNotFoundException("Hostel not found"));

            WardenDetails warden = wardenDetailsMapper.toEntity(createWardenDto);
            warden.setUser(user);
            warden.setEmail(user.getEmail());

            wardenDetailsRepository.save(warden);
        } catch (UsernameNotFoundException | ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WardenProfileDto getWardenDetails(Long wardenId) {
        WardenDetails warden = wardenDetailsRepository.findById(wardenId).orElseThrow(() -> new ResourceNotFoundException("Warden not found"));
        return wardenDetailsMapper.toProfileDto(warden);
    }

    @Override
    public List<RoomDto> getRooms(Long wardenId) {
        WardenDetails warden = wardenDetailsRepository.findById(wardenId).orElseThrow(() -> new ResourceNotFoundException("Warden not found"));
//        System.out.println("Warden ID: " + wardenId);
        Hostel hostel = warden.getHostel();
//        System.out.println("Hostel ID: " + hostel.getId());
//        System.out.println("Hostel Name: " + hostel.getName());
        List<Room> rooms = roomRepository.findByHostelId(hostel.getId());
        return rooms.stream()
                .map(roomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateRoomAvailability(Long roomId, boolean available) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        room.setAvailable(available);
        roomRepository.save(room);
    }

    @Override
    public List<StudentSummaryDto> getHostelStudents(Long wardenId) {
        WardenDetails warden = wardenDetailsRepository.findById(wardenId).orElseThrow(() -> new ResourceNotFoundException("Warden not found"));
        Hostel hostel = warden.getHostel();
        List<StudentDetails> students = studentDetailsRepository.findByHostelId(hostel.getId());
        return students.stream().map(studentMapper::toDto).collect(Collectors.toList());
    }

    //TODO
    @Override
    public StudentDetailsDto getStudentDetailsById(Long studentId, Long wardenId) {

        // Fetch student details
        StudentDetails student = studentDetailsRepository.findById(studentId)
                .orElse(null);

        return studentMapper.toDto1(student);
    }

    @Override
    public List<BookingDto> getPendingBookings(Long wardenId) {
        // Get the hostel assigned to this warden
        Hostel hostel = wardenDetailsRepository.findById(wardenId)
                .orElseThrow(() -> new ResourceNotFoundException("Warden not found"))
                .getHostel();

        // Get all pending bookings
        Iterable<Booking> bookings = bookingRepository.findBookingsByStatus();

        // Filter bookings for the warden's hostel and map to DTO
        return StreamSupport.stream(bookings.spliterator(), false)
                .filter(booking -> booking.getStudentDetails().getHostel().getId().equals(hostel.getId()))
                .map(bookingMapper::toDto)  // assumes you have a BookingMapper with toDto()
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void approveBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        StudentDetails studentDetails = booking.getStudentDetails();
        booking.setStatus(BookingStatus.APPROVED);
        booking.setApprovalDate(LocalDate.now());
        studentDetails.setHostel(booking.getRoom().getHostel());
        studentDetails.setRoom(booking.getRoom());
        bookingRepository.save(booking);
    }

    @Override
    public void rejectBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        booking.setStatus(BookingStatus.REJECTED);
        bookingRepository.save(booking);
    }

    @Override
    public List<PaymentDto> getAllPayments(Long wardenId) {
        return List.of();
    }

    @Override
    public void addRoom(RoomDto room, Long wardenId) {
        WardenDetails warden = wardenDetailsRepository.findById(wardenId).orElseThrow(() -> new ResourceNotFoundException("Warden not found"));

        Room newRoom = new Room();
        newRoom.setHostel(warden.getHostel());
        newRoom.setRoomNumber(room.roomNumber());
        newRoom.setAvailable(room.available());
        newRoom.setCapacity(room.capacity());
        roomRepository.save(newRoom);
    }
}
