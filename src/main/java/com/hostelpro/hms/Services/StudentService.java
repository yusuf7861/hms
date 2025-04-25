package com.hostelpro.hms.services;

import com.hostelpro.hms.dto.PaymentDto;
import com.hostelpro.hms.dto.StudentDetailsDto;
import com.hostelpro.hms.dto.UpdateStudentDetailsDto;
import com.hostelpro.hms.mapper.BookingInfo;
import com.hostelpro.hms.mapper.StudentDetailsInfo;

import java.util.List;

public interface StudentService {
    void addStudentDetails(StudentDetailsDto studentDetailsDto, Long UserId);
    List<StudentDetailsInfo> getAllStudentDetails();
    StudentDetailsDto getStudentDetailsById(Long id);
    void updateStudentDetails(Long id, UpdateStudentDetailsDto updateStudentDetailsDto);
    void addBooking(Long roomId, Long studentId);
    PaymentDto doPayment(Long studentId, PaymentDto paymentDto);
}
