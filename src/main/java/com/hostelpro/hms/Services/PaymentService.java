package com.hostelpro.hms.services;

import com.hostelpro.hms.dto.PaymentDto;
import java.util.List;

public interface PaymentService {
    List<PaymentDto> getAllPaymentsByStudentId(Long studentId);
}
