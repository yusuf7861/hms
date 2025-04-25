package com.hostelpro.hms.repositories;

import com.hostelpro.hms.dto.StudentDetailsDto;
import com.hostelpro.hms.entities.StudentDetails;
import com.hostelpro.hms.entities.User;
import com.hostelpro.hms.mapper.StudentDetailsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetails, Long>, JpaSpecificationExecutor<StudentDetails> {
    Long user(User user);
    List<StudentDetailsInfo> findAllBy();
    StudentDetailsDto getStudentDetailsById(Long id);
    List<StudentDetails> findByHostelId(Long id);
}