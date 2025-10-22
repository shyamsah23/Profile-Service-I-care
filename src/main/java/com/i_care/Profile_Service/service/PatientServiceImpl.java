package com.i_care.Profile_Service.service;

import com.i_care.Profile_Service.client.NotificationFeignClient;
import com.i_care.Profile_Service.dto.EmailWithHtmlDTO;
import com.i_care.Profile_Service.dto.PatientDTO;
import com.i_care.Profile_Service.entity.Doctor;
import com.i_care.Profile_Service.entity.Patient;
import com.i_care.Profile_Service.exception.ProfileException;
import com.i_care.Profile_Service.repository.PatientRepository;
import com.i_care.Profile_Service.utility.NotificationConstants;
import com.i_care.Profile_Service.utility.ProfileConstants;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    NotificationFeignClient notificationFeignClient;

    @Override
    public Long addPatient(PatientDTO patientDTO) throws ProfileException {
        logger.info("Checking if Patient with name = {} & other provided details exists already or not", patientDTO.getName());
        if (patientDTO.getEmail() != null && patientRepository.findByEmail(patientDTO.getEmail()).isPresent()) {
            logger.info(" Patient already exixts");
            throw new ProfileException(ProfileConstants.PATIENT_ALREADY_EXISTS);
        }

        if (patientDTO.getAadhaarNo() != null && patientRepository.findByAadhaarNo(patientDTO.getAadhaarNo()).isPresent()) {
            logger.info(" Patient already exixts");
            throw new ProfileException(ProfileConstants.PATIENT_ALREADY_EXISTS);
        }
        logger.info(" Patient name = {} saved to the system", patientDTO.getName());
        EmailWithHtmlDTO emailWithHtmlDTO = new EmailWithHtmlDTO(patientDTO.getId(),patientDTO.getEmail(), NotificationConstants.PATIENT_REGISTER_SUBJECT, NotificationConstants.PATIENT_REGISTER);
        notificationFeignClient.sendMailWithHTML(emailWithHtmlDTO);
        logger.info("Mail sent successfully");
        return patientRepository.save(patientDTO.toEntity()).getId();
    }

    @Override
    public PatientDTO getPatientById(Long id) throws ProfileException {
        Patient patient = patientRepository.findById(id).orElse(null);
        logger.info("Trying to find Patient with id = {} in system", id);
        if (patient == null) {
            logger.info("Patient with id = {} not found in system", id);
            throw new ProfileException(ProfileConstants.PATIENT_NOT_FOUND);
        } else {
            logger.info("Patient with id = {} found in system", id);
            return patient.toDTO();
        }
    }

}
