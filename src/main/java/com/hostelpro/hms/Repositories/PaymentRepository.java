package com.hostelpro.hms.repositories;

import com.hostelpro.hms.dto.PaymentDto;
import com.hostelpro.hms.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>, JpaSpecificationExecutor<Payment> {
    List<Payment> findByStudentDetails_Id(Long studentDetailsId);

    Payment findPaymentByPaymentDate(LocalDate paymentDate);

    List<PaymentDto> findAllByStudentDetails_Id(Long studentDetailsId);
//    Payment findPaymentByStatus(String paymentStatus);
}