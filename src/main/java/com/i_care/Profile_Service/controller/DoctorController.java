package com.i_care.Profile_Service.controller;

import com.i_care.Profile_Service.dto.DoctorDTO;
import com.i_care.Profile_Service.entity.Doctor;
import com.i_care.Profile_Service.exception.ProfileException;
import com.i_care.Profile_Service.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/profile/doctor")
@Validated
public class DoctorController {

    Logger logger = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity<Long> addDoctor(@RequestBody DoctorDTO doctorDTO) throws ProfileException {
        logger.info("Adding Doctor - name = {}", doctorDTO.getName());
        Long id = doctorService.addDoctor(doctorDTO);
        logger.info("Successfully Added Doctor to the Record");
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) throws ProfileException {
        logger.info("Getting doctor info with id = {}", id);
        DoctorDTO doctorDetails = doctorService.getDoctorById(id);
        logger.info("Fetched Doctor Details Successfully");
        return new ResponseEntity<>(doctorDetails, HttpStatus.OK);

    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> doctorExists(@PathVariable Long id) throws ProfileException{
        return new ResponseEntity<>(doctorService.doctorExists(id),HttpStatus.OK);
    }

    @GetMapping("/all-doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        logger.info("Fetching all List Of Doctors");
        List<Doctor> listOfDoctors = doctorService.getAllDoctor();
        logger.info("Fetched List of doctorr with size = {}",listOfDoctors.size());
        return new ResponseEntity<>(listOfDoctors,HttpStatus.OK);
    }
}
