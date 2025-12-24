package com.i_care.Profile_Service.service;

import com.i_care.Profile_Service.client.NotificationFeignClient;
import com.i_care.Profile_Service.dto.AdminDTO;
import com.i_care.Profile_Service.dto.EmailWithHtmlDTO;
import com.i_care.Profile_Service.entity.Admin;
import com.i_care.Profile_Service.entity.Doctor;
import com.i_care.Profile_Service.exception.ProfileException;
import com.i_care.Profile_Service.repository.AdminRepository;
import com.i_care.Profile_Service.utility.NotificationConstants;
import com.i_care.Profile_Service.utility.ProfileConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private NotificationFeignClient notificationFeignClient;

    @Override
    public Long addAdmin(AdminDTO adminDTO) throws ProfileException {
        logger.info("Checking if Admin record with name = {} & other provided details exists already or not", adminDTO.getName());
        if (adminDTO.getEmail()!= null && adminRepository.findByEmail(adminDTO.getEmail())!=null) {
            logger.info("admin already exits");
            throw new ProfileException(ProfileConstants.ADMIN_ALREADY_EXISTS);
        }
        Long id = adminRepository.save(adminDTO.toEntity()).getId();
        adminDTO.setId(id);
        logger.info("admin name = {} with id={} saved to the system", adminDTO.getName(), adminDTO.getId());
        EmailWithHtmlDTO emailWithHtmlDTO = new EmailWithHtmlDTO(adminDTO.getId(), adminDTO.getEmail(), NotificationConstants.ADMIN_REGISTER_SUBJECT, NotificationConstants.ADMIN__REGISTER);
        notificationFeignClient.sendMailWithHTML(emailWithHtmlDTO);
        logger.info("Mail sent successfully");
        return id;
    }

    @Override
    public AdminDTO getAdminById(Long id) throws ProfileException {
        Admin admin = adminRepository.findById(id).orElse(null);
        logger.info("Trying to find Admin with id = {} in system", id);
        if (admin == null) {
            logger.info("Admin with id = {} not found in system", id);
            throw new ProfileException(ProfileConstants.ADMIN_NOT_FOUND);
        } else {
            logger.info("Admin with id = {} found in system", id);
            return admin.toDTO();
        }
    }
}
