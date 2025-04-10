package com.hostelpro.hms.Services.ServiceImplementation;

import com.hostelpro.hms.DTOs.RegisterBookingDTO;
import com.hostelpro.hms.DTOs.StudentDetailsDto;
import com.hostelpro.hms.DTOs.UpdateStudentDetailsDto;
import com.hostelpro.hms.Entities.StudentDetails;
import com.hostelpro.hms.Entities.User;
import com.hostelpro.hms.Mapper.StudentDetailsInfo;
import com.hostelpro.hms.Repositories.StudentDetailsRepository;
import com.hostelpro.hms.Repositories.UserRepository;
import com.hostelpro.hms.Services.StudentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentDetailsService {

    private final UserRepository userRepository;
    private StudentDetailsDto studentDetailsDto;
    @Autowired
    private StudentDetailsRepository studentDetailsRepository;

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
    public void updateStudentDetails(Long id, UpdateStudentDetailsDto updateStudentDetailsDto) {
        StudentDetails savedStudent = studentDetailsRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found."));

        savedStudent.setGuardianContactNumber(updateStudentDetailsDto.guardianContactNumber());
        savedStudent.setDepartment(updateStudentDetailsDto.department());
        savedStudent.setAddress(updateStudentDetailsDto.address());
        savedStudent.setCollegeName(updateStudentDetailsDto.collegeName());
        savedStudent.setPhone(updateStudentDetailsDto.phone());
        studentDetailsRepository.save(savedStudent);
    }

    @Override
    public RegisterBookingDTO addBooking(RegisterBookingDTO dto, Long studentId) {

        return null;
    }

}
