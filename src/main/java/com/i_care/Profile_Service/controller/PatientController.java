package com.i_care.Profile_Service.controller;

import com.i_care.Profile_Service.dto.PatientDTO;
import com.i_care.Profile_Service.exception.ProfileException;
import com.i_care.Profile_Service.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/profile/patient")
@Validated
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/add")
    public ResponseEntity<Long>addPatient(@RequestBody PatientDTO patientDTO) throws ProfileException {
        return new ResponseEntity<>(patientService.addPatient( patientDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PatientDTO>getPatientById(@PathVariable Long id) throws ProfileException {
        return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.CREATED);
    }
}
