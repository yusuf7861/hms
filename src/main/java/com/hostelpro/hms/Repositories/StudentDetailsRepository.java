package com.hostelpro.hms.Repositories;

import com.hostelpro.hms.DTOs.StudentDetailsDto;
import com.hostelpro.hms.Entities.StudentDetails;
import com.hostelpro.hms.Entities.User;
import com.hostelpro.hms.Mapper.StudentDetailsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetails, Long>, JpaSpecificationExecutor<StudentDetails> {
    Long user(User user);
    List<StudentDetailsInfo> findAllBy();
    StudentDetailsDto getStudentDetailsById(Long id);
}