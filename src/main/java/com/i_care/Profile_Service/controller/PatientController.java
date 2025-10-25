package com.i_care.Profile_Service.controller;

import com.i_care.Profile_Service.dto.PatientDTO;
import com.i_care.Profile_Service.exception.ProfileException;
import com.i_care.Profile_Service.service.PatientService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin
@RequestMapping("/profile/patient")
@Validated
public class PatientController {

    Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @PostMapping("/add")
    public ResponseEntity<Long> addPatient(@RequestBody PatientDTO patientDTO) throws ProfileException {
        logger.info("Adding Patient - name = {}", patientDTO.getName());
        Long id = patientService.addPatient(patientDTO);
        logger.info("Successfully Added Patient to the Record");
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) throws ProfileException {
        logger.info("Getting Patient info by id = {}", id);
        PatientDTO patientDetails = patientService.getPatientById(id);
        logger.info("Fetched Patient Details Successfully");
        return new ResponseEntity<>(patientDetails, HttpStatus.OK);
    }
}
