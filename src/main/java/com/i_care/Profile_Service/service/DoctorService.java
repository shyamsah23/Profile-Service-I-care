package com.i_care.Profile_Service.service;

import com.i_care.Profile_Service.dto.DoctorDTO;
import com.i_care.Profile_Service.dto.PatientDTO;
import com.i_care.Profile_Service.entity.Doctor;
import com.i_care.Profile_Service.exception.ProfileException;

import javax.print.Doc;
import java.util.List;

public interface DoctorService {
    public Long addDoctor(DoctorDTO doctorDTO) throws ProfileException;

    public void updateDoctor(DoctorDTO doctor) throws ProfileException;

    public DoctorDTO getDoctorById(Long id) throws ProfileException;

    public Boolean doctorExists(Long id) throws ProfileException;

    public List<Doctor> getAllDoctor();

    public List<Doctor> getAllDoctorsByDepartment(String department);
}
