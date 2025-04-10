package com.hostelpro.hms.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentDetails {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @NotBlank
    private String name;
    @NotBlank
    private String guardianName;
    @NotBlank
    private String guardianContactNumber;

    @NotBlank
    private String gender;

    @Pattern(regexp = "\\d{10}")
    private String phone;

    private String department;

    private String address;

    @NotBlank
    private String collegeName;

    @OneToOne(mappedBy = "studentDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Booking bookings;

    @OneToMany(mappedBy = "studentDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hostel_id")  // foreign key column
    private Hostel hostel;

    public void setStudentId(Long userId) {
    }

}