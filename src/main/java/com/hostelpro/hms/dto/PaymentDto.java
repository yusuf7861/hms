package com.hostelpro.hms.dto;

import com.hostelpro.hms.entities.Payment;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Payment}
 */
public record PaymentDto(Long id, @NotBlank String month, @Min(2000) @Max(2100) int year, double amount, LocalDate paymentDate) implements Serializable {
  }