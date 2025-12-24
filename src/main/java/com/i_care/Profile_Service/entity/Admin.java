package com.i_care.Profile_Service.entity;

import com.i_care.Profile_Service.Enums.BloodGroup;
import com.i_care.Profile_Service.dto.AdminDTO;
import com.i_care.Profile_Service.dto.PatientDTO;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Admin")
public class Admin{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;

    public Admin(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Admin() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AdminDTO toDTO() {
        return new AdminDTO(this.id, this.name, this.email, this.phone);
    }
}
