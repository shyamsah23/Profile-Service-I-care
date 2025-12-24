package com.i_care.Profile_Service.dto;

import com.i_care.Profile_Service.entity.Admin;

public class AdminDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;

    public AdminDTO(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

        public AdminDTO() {
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

    public Admin toEntity(){
        return new Admin(this.id,this.name,this.email,this.phone);
    }
}
