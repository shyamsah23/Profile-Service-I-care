package com.i_care.Profile_Service.service;

import com.i_care.Profile_Service.dto.DoctorDTO;
import com.i_care.Profile_Service.exception.ProfileException;
import com.i_care.Profile_Service.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Long addDoctor(DoctorDTO doctorDTO) throws ProfileException {
        if(doctorDTO.getEmail()!=null&& doctorRepository.findByEmail(doctorDTO.getEmail()).isPresent()) throw new ProfileException("DOCTOR_ALREADY_EXISTS");
        if(doctorDTO.getLicenseNo()!=null&& doctorRepository.findByLicenseNo(doctorDTO.getLicenseNo()).isPresent()) throw new ProfileException("DOCTOR_ALREADY_EXISTS");
        return doctorRepository.save(doctorDTO.toEntity()).getId();
    }

    @Override
    public DoctorDTO getDoctorById(Long id) throws ProfileException{
        return doctorRepository.findById(id).orElseThrow(()->new ProfileException("DOCTOR_NOT_FOUND")).toDTO();
    }
}
