package com.hostelpro.hms.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentDetails studentDetails;

    @NotBlank
    private String month;  // e.g., "January"

    @Min(2000)
    @Max(2100)
    private int year;

    @DecimalMin(value = "0.0", inclusive = false)
    private double amount;

    private LocalDate paymentDate = LocalDate.now();
}
