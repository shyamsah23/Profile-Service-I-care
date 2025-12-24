package com.i_care.Profile_Service.service;

import com.i_care.Profile_Service.client.NotificationFeignClient;
import com.i_care.Profile_Service.dto.EmailWithHtmlDTO;
import com.i_care.Profile_Service.dto.PatientDTO;
import com.i_care.Profile_Service.entity.Patient;
import com.i_care.Profile_Service.exception.ProfileException;
import com.i_care.Profile_Service.repository.PatientRepository;
import com.i_care.Profile_Service.utility.NotificationConstants;
import com.i_care.Profile_Service.utility.ProfileConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private NotificationFeignClient notificationFeignClient;

    @Override
    public Long addPatient(PatientDTO patientDTO) throws ProfileException {
        logger.info("Checking if Patient with name = {} & other provided details exists already or not", patientDTO.getName());
        if (patientDTO.getEmail() != null && patientRepository.findByEmail(patientDTO.getEmail())!=null) {
            logger.info(" Patient already exists");
            throw new ProfileException(ProfileConstants.PATIENT_ALREADY_EXISTS);
        }

        if (patientDTO.getAadhaarNo() != null && patientRepository.findByAadhaarNo(patientDTO.getAadhaarNo()).isPresent()) {
            logger.info(" Patient already exists");
            throw new ProfileException(ProfileConstants.PATIENT_ALREADY_EXISTS);
        }
        long id = patientRepository.save(patientDTO.toEntity()).getId();
        patientDTO.setId(id);
        logger.info(" Patient name = {} saved to the system with id={}", patientDTO.getName(), patientDTO.getId());
        EmailWithHtmlDTO emailWithHtmlDTO = new EmailWithHtmlDTO(id, patientDTO.getEmail(), NotificationConstants.PATIENT_REGISTER_SUBJECT, NotificationConstants.PATIENT_REGISTER);
        notificationFeignClient.sendMailWithHTML(emailWithHtmlDTO);
        logger.info("Mail sent successfully");
        return id;
    }

    @Override
    public void updatePatient(PatientDTO patient) throws ProfileException {
        if(!patientRepository.existsById(patient.getId())) throw new ProfileException(ProfileConstants.PATIENT_NOT_FOUND);
        PatientDTO existingPatient =patientRepository.findByEmail(patient.getEmail()).toDTO();
        existingPatient.setDob(patient.getDob());
        existingPatient.setAddress(patient.getAddress());
        existingPatient.setAadhaarNo(patient.getAadhaarNo());
        existingPatient.setPhone(patient.getPhone());
        existingPatient.setBloodGroup(patient.getBloodGroup());

        patientRepository.save(existingPatient.toEntity());
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

    @Override
    public Boolean patientExists(Long id) throws ProfileException {
        return patientRepository.existsById(id);
    }

    @Override
    public List<PatientDTO> getAllPatients() throws ProfileException {
        List<PatientDTO>patients=patientRepository.findAll().stream().map(Patient::toDTO).toList();
        return patients;
    }
}
