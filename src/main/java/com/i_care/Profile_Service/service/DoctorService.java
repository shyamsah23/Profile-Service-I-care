package com.i_care.Profile_Service.service;

import com.i_care.Profile_Service.dto.DoctorDTO;
import com.i_care.Profile_Service.exception.ProfileException;

public interface DoctorService {
    public Long addDoctor(DoctorDTO doctorDTO) throws ProfileException;

    public DoctorDTO getDoctorById(Long id) throws ProfileException;
}
