package com.i_care.Profile_Service.controller;

import com.i_care.Profile_Service.dto.DoctorDTO;
import com.i_care.Profile_Service.exception.ProfileException;
import com.i_care.Profile_Service.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/profile/doctor")
@Validated
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity<Long> addDoctor(@RequestBody DoctorDTO doctorDTO) throws ProfileException {
        return new ResponseEntity<>(doctorService.addDoctor( doctorDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DoctorDTO>getDoctorById(@PathVariable Long id) throws ProfileException {
        return new ResponseEntity<>(doctorService.getDoctorById(id), HttpStatus.CREATED);
    }
}
