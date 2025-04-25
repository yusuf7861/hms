package com.hostelpro.hms.services.serviceimpl;

import com.hostelpro.hms.dto.PaymentDto;
import com.hostelpro.hms.dto.StudentDetailsDto;
import com.hostelpro.hms.dto.UpdateStudentDetailsDto;
import com.hostelpro.hms.entities.*;
import com.hostelpro.hms.entities.Enum.BookingStatus;
import com.hostelpro.hms.mapper.StudentDetailsInfo;
import com.hostelpro.hms.repositories.*;
import com.hostelpro.hms.services.PaymentService;
import com.hostelpro.hms.services.StudentService;
import jdk.jfr.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.ResolutionException;
import java.time.LocalDate;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService, PaymentService {

    private final UserRepository userRepository;
    private StudentDetailsDto studentDetailsDto;
    @Autowired
    private StudentDetailsRepository studentDetailsRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    public StudentServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addStudentDetails(StudentDetailsDto studentDetailsDto, Long UserId) {
        try {
            User user = userRepository.findById(UserId).orElseThrow(() -> new UsernameNotFoundException("User with ID " + UserId + " not found"));
            StudentDetails student = new StudentDetails();
            student.setName(studentDetailsDto.name());
            student.setGender(studentDetailsDto.gender());
            student.setGuardianName(studentDetailsDto.guardianName());
            student.setGuardianContactNumber(studentDetailsDto.guardianContactNumber());
            student.setPhone(studentDetailsDto.phone());
            student.setAddress(studentDetailsDto.address());
            student.setCollegeName(studentDetailsDto.collegeName());
            student.setDepartment(studentDetailsDto.department());

            student.setUser(user);
            studentDetailsRepository.save(student);
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to save student details: " + e.getMessage());
        }
    }


    @Override
    public List<StudentDetailsInfo> getAllStudentDetails() {
        return studentDetailsRepository.findAllBy();
    }

    @Override
    public StudentDetailsDto getStudentDetailsById(Long id) {
        return studentDetailsRepository.getStudentDetailsById(id);
    }

    @Override
    public void updateStudentDetails(Long id, UpdateStudentDetailsDto dto) {
        StudentDetails savedStudent = studentDetailsRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found."));

        if(dto.phone() != null)
        {
            savedStudent.setPhone(dto.phone());
        }
        if(dto.guardianContactNumber() != null)
        {
            savedStudent.setGuardianContactNumber(dto.guardianContactNumber());
        }
        if(dto.address() != null)
        {
            savedStudent.setAddress(dto.address());
        }
        studentDetailsRepository.save(savedStudent);
    }

    @Transactional
    @Override
    public void addBooking(Long roomId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found"));

        if (user.getStudentDetails() == null) {
            throw new IllegalArgumentException("User does not have student details");
        }

        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResolutionException("Room not found"));

        Booking newbooking = new Booking();
        newbooking.setStudentDetails(user.getStudentDetails());
        newbooking.setActive(true);
        newbooking.setApprovalDate(null);
        newbooking.setRequestDate(LocalDate.now());
        newbooking.setStatus(BookingStatus.PENDING);
        newbooking.setRoom(room);

        bookingRepository.save(newbooking);
    }

    @Override
    public PaymentDto doPayment(Long studentId, PaymentDto dto) {
        StudentDetails studentDetails = studentDetailsRepository.findById(studentId).orElseThrow(() -> new UsernameNotFoundException("student not found."));

        Payment payment = new Payment();
        payment.setStudentDetails(studentDetails);
        payment.setMonth(dto.month());
        payment.setYear(dto.year());
        payment.setAmount(dto.amount());
        payment.setPaymentDate(LocalDate.now());

        Payment savedPayment = paymentRepository.save(payment);

        // Return the PaymentDto right after saving
        return new PaymentDto(
                savedPayment.getId(),
                savedPayment.getMonth(),
                savedPayment.getYear(),
                savedPayment.getAmount(),
                savedPayment.getPaymentDate()
        );
    }

    // from PaymentService
    @Override
    public List<PaymentDto> getAllPaymentsByStudentId(Long studentId) {
        return paymentRepository.findAllByStudentDetails_Id(studentId);
    }
}
