package com.i_care.Profile_Service.repository;

import com.i_care.Profile_Service.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    public Optional<Patient> findByEmail(String email);

    public Optional<Patient> findByAadhaarNo(String aadhaarNo);
}
