package com.i_care.Profile_Service.controller;

import com.i_care.Profile_Service.dto.PatientDTO;
import com.i_care.Profile_Service.dto.ResponseDTO;
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

import java.util.List;

@RestController
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
        logger.info("Successfully Added Patient in the Record");
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO>  updatePatient(@RequestBody PatientDTO patientDTO) throws ProfileException {
        logger.info("Updating Patient info with - id = {}", patientDTO.getId());
        patientService.updatePatient(patientDTO);
        logger.info("Successfully Updated Patient in the Record");
        return new ResponseEntity<>(new ResponseDTO("Profile updated Successfully"),HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) throws ProfileException {
        logger.info("Getting Patient info by id = {}", id);
        PatientDTO patientDetails = patientService.getPatientById(id);
        logger.info("Fetched Patient Details Successfully");
        return new ResponseEntity<>(patientDetails, HttpStatus.OK);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> patientExists(@PathVariable Long id) throws ProfileException {
        return new ResponseEntity<>(patientService.patientExists(id), HttpStatus.OK);
    }

    @GetMapping("/all-patients")
    public ResponseEntity<List<PatientDTO>> getAllPatients() throws ProfileException{
        logger.info("Fetching all List Of Patients");
        List<PatientDTO> listOffPatients=patientService.getAllPatients();
        logger.info("Fetched List of patients with size = {}",listOffPatients.size());
        return new ResponseEntity<>(listOffPatients,HttpStatus.OK);
    }
}
