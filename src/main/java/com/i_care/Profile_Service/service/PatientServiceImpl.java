package com.i_care.Profile_Service.service;

import com.i_care.Profile_Service.dto.PatientDTO;
import com.i_care.Profile_Service.exception.ProfileException;
import com.i_care.Profile_Service.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    PatientRepository patientRepository;

    @Override
    public Long addPatient(PatientDTO patientDTO) throws ProfileException {
        if(patientDTO.getEmail()!=null&& patientRepository.findByEmail(patientDTO.getEmail()).isPresent()) throw new ProfileException("PATIENT_ALREADY_EXISTS");
        if(patientDTO.getAadhaarNo()!=null&& patientRepository.findByAadhaarNo(patientDTO.getAadhaarNo()).isPresent()) throw new ProfileException("PATIENT_ALREADY_EXISTS");
        return patientRepository.save(patientDTO.toEntity()).getId();
    }

    @Override
    public PatientDTO getPatientById(Long id) throws ProfileException {
        return patientRepository.findById(id).orElseThrow(()->new ProfileException("PATIENT_NOT_FOUND")).toDTO();
    }

}
