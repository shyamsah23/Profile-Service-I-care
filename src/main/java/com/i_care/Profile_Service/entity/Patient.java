package com.i_care.Profile_Service.entity;

import com.i_care.Profile_Service.Enums.BloodGroup;
import com.i_care.Profile_Service.dto.PatientDTO;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "Patient")
public class Patient{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private LocalDate dob;
    @Column(unique = true)
    private String phone;
    private String address;
    @Column(unique = true)
    private String aadhaarNo;
    private BloodGroup bloodGroup;


    public Patient(Long id, String name, String email, LocalDate dob, String phone, String address, String aadhaarNo, BloodGroup bloodGroup) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
        this.aadhaarNo = aadhaarNo;
        this.bloodGroup = bloodGroup;
    }

    public Patient() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAadhaarNo() {
        return aadhaarNo;
    }

    public void setAadhaarNo(String aadhaarNo) {
        this.aadhaarNo = aadhaarNo;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public PatientDTO toDTO() {
        return new PatientDTO(this.id, this.name, this.email, this.dob, this.phone, this.address, this.aadhaarNo, this.bloodGroup);
    }
}