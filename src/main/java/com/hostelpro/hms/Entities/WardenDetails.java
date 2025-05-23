package com.hostelpro.hms.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WardenDetails {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @NotBlank
    private String name;
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank
    private LocalDate joining_date;

    @Pattern(
            regexp = "^(\\+91[-\\s]?)?\\d{10}$",
            message = "Contact number must be 10 digits, optionally starting with +91")
    @Column(length = 13, nullable = false)
    private String contactNumber;
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hostel_id", nullable = false)
    private Hostel hostel;
}
