package com.i_care.Profile_Service.repository;

import com.i_care.Profile_Service.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    public Doctor findByEmail(String email);

    public Optional<Doctor> findByLicenseNo(String licenseNo);

    public List<Doctor> findBySpecialization(String department);
}
