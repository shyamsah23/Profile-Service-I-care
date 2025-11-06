package com.i_care.Profile_Service.service;

import com.i_care.Profile_Service.dto.PatientDTO;
import com.i_care.Profile_Service.exception.ProfileException;

public interface PatientService {
    public Long addPatient(PatientDTO patient) throws ProfileException;

    public PatientDTO getPatientById(Long id) throws ProfileException;

    public Boolean patientExists(Long id) throws ProfileException;
}
