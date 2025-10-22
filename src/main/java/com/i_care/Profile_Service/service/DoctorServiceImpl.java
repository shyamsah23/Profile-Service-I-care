package com.i_care.Profile_Service.service;

import com.i_care.Profile_Service.client.NotificationFeignClient;
import com.i_care.Profile_Service.dto.DoctorDTO;
import com.i_care.Profile_Service.dto.EmailWithHtmlDTO;
import com.i_care.Profile_Service.entity.Doctor;
import com.i_care.Profile_Service.exception.ProfileException;
import com.i_care.Profile_Service.repository.DoctorRepository;
import com.i_care.Profile_Service.utility.NotificationConstants;
import com.i_care.Profile_Service.utility.ProfileConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {

    Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private NotificationFeignClient notificationFeignClient;

    @Override
    public Long addDoctor(DoctorDTO doctorDTO) throws ProfileException {
        logger.info("Checking if Doctor record with name = {} & other provided details exists already or not", doctorDTO.getName());
        if (doctorDTO.getEmail() != null && doctorRepository.findByEmail(doctorDTO.getEmail()).isPresent()) {
            logger.info(" Doctor already exixts");
            throw new ProfileException(ProfileConstants.DOCTOR_ALREADY_EXISTS);
        }
        if (doctorDTO.getLicenseNo() != null && doctorRepository.findByLicenseNo(doctorDTO.getLicenseNo()).isPresent()) {
            logger.info(" Doctor already exixts");
            throw new ProfileException(ProfileConstants.DOCTOR_ALREADY_EXISTS);
        }
        logger.info(" Doctor name = {} saved to the system", doctorDTO.getName());
        EmailWithHtmlDTO emailWithHtmlDTO = new EmailWithHtmlDTO(doctorDTO.getEmail(), "Doctor", NotificationConstants.DOCTOR_REGISTER);
        notificationFeignClient.sendMailWithHTML(emailWithHtmlDTO);
        logger.info("Mail sent successfully");
        return doctorRepository.save(doctorDTO.toEntity()).getId();
    }

    @Override
    public DoctorDTO getDoctorById(Long id) throws ProfileException {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        logger.info("Trying to find Doctor with id = {} in system", id);
        if (doctor == null) {
            logger.info("Doctor with id = {} not found in system", id);
            throw new ProfileException(ProfileConstants.DOCTOR_NOT_FOUND);
        } else {
            logger.info("Doctor with id = {} found in system", id);
            return doctor.toDTO();
        }
    }
}
