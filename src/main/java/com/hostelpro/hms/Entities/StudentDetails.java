package com.hostelpro.hms.entities;

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
    @Pattern(regexp = "^(\\+91)?\\d{10}$", message = "Contact number must be 10 digits, optionally starting with +91")
    @Column(length = 13, nullable = false)
    private String guardianContactNumber;

    @NotBlank
    private String gender;

    @Pattern(regexp = "^(\\+91[-\\s]?)?\\d{10}$", message = "Contact number must be 10 digits, optionally starting with +91")
    @Column(length = 13, nullable = false)
    private String phone;

    private String department;

    private String address;

    private String email;

    @NotBlank
    private String collegeName;

    @OneToMany(mappedBy = "studentDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hostel_id")  // foreign key column
    private Hostel hostel;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}