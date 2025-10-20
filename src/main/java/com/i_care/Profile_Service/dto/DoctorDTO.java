package com.i_care.Profile_Service.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class DoctorDTO {
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private LocalDate dob;
    private String phone;
    private String address;
    @Column(unique = true)
    private String aadhaarNo;
    private String specialization;
    private String department;
    private Integer totalExp;
}
