package com.i_care.Profile_Service.controller;

import com.i_care.Profile_Service.dto.AdminDTO;
import com.i_care.Profile_Service.dto.DoctorDTO;
import com.i_care.Profile_Service.exception.ProfileException;
import com.i_care.Profile_Service.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile/admin")
@Validated
public class AdminController {

    Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @PostMapping("/add")
    public ResponseEntity<Long> addAdmin(@RequestBody AdminDTO adminDTO) throws ProfileException {
        logger.info("Adding Admin - name = {}", adminDTO.getName());
        Long id = (adminService.addAdmin(adminDTO));
        logger.info("Successfully Added admin to the Record");
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id) throws ProfileException {
        logger.info("Getting admin info with id = {}", id);
        AdminDTO adminDetails = adminService.getAdminById(id);
        logger.info("Fetched Admin Details Successfully");
        return new ResponseEntity<>(adminDetails, HttpStatus.OK);
    }
}
