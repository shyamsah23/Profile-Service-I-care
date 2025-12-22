package com.i_care.Profile_Service.repository;

import com.i_care.Profile_Service.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    public Admin findByEmail(String email);

    public Optional<Admin> findByPhone(String phone);
}
