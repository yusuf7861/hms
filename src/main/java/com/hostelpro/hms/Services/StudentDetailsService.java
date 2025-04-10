package com.hostelpro.hms.Services;

import com.hostelpro.hms.DTOs.RegisterBookingDTO;
import com.hostelpro.hms.DTOs.StudentDetailsDto;
import com.hostelpro.hms.DTOs.UpdateStudentDetailsDto;
import com.hostelpro.hms.Mapper.StudentDetailsInfo;

import java.util.List;

public interface StudentDetailsService {
    void addStudentDetails(StudentDetailsDto studentDetailsDto, Long UserId);
    List<StudentDetailsInfo> getAllStudentDetails();
    StudentDetailsDto getStudentDetailsById(Long id);
    void updateStudentDetails(Long id, UpdateStudentDetailsDto updateStudentDetailsDto);
    RegisterBookingDTO addBooking(RegisterBookingDTO dto, Long studentId);
}
