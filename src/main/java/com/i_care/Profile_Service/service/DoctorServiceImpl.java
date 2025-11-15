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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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
            logger.info("Doctor already exits");
            throw new ProfileException(ProfileConstants.DOCTOR_ALREADY_EXISTS);
        }
        if (doctorDTO.getLicenseNo() != null && doctorRepository.findByLicenseNo(doctorDTO.getLicenseNo()).isPresent()) {
            logger.info("Doctor already exits");
            throw new ProfileException(ProfileConstants.DOCTOR_ALREADY_EXISTS);
        }
        Long id = doctorRepository.save(doctorDTO.toEntity()).getId();
        doctorDTO.setId(id);
        logger.info("Doctor name = {} with id={} saved to the system", doctorDTO.getName(), doctorDTO.getId());
        EmailWithHtmlDTO emailWithHtmlDTO = new EmailWithHtmlDTO(doctorDTO.getId(), doctorDTO.getEmail(), NotificationConstants.DOCTOR_REGISTER_SUBJECT, NotificationConstants.DOCTOR_REGISTER);
        notificationFeignClient.sendMailWithHTML(emailWithHtmlDTO);
        logger.info("Mail sent successfully");
        return id;
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

    @Override
    public Boolean doctorExists(Long id) throws ProfileException {
        return doctorRepository.existsById(id);
    }

    @Override
    @Cacheable(value = "allDoctors")
    public List<Doctor> getAllDoctor() {
        logger.info("Started Fetching List of Doctors");
        List<Doctor> listOfAllDoctors = doctorRepository.findAll();
        logger.info("Fetch all doctors list Successfully");
        return listOfAllDoctors;
    }

    @Override
    @Cacheable(value = "doctorsByDepartment")
    public List<Doctor> getAllDoctorsByDepartment(String department) {
        logger.info("Started Fetching List of doctors By Department -> In Service");
        List<Doctor> listOfDoctorsByDepartment = doctorRepository.findBySpecialization(department);
        logger.info("Fetched Doctors by Department");
        return listOfDoctorsByDepartment;
    }
}
