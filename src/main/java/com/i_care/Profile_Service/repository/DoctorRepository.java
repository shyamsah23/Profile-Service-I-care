package com.i_care.Profile_Service.repository;

import com.i_care.Profile_Service.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    public Optional<Doctor> findByEmail(String email);

    public Optional<Doctor> findByLicenseNo(String licenseNo);
}
