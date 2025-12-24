package com.i_care.Profile_Service.service;

import com.i_care.Profile_Service.dto.AdminDTO;
import com.i_care.Profile_Service.exception.ProfileException;

public interface AdminService {
    public Long addAdmin(AdminDTO doctorDTO) throws ProfileException;
    public AdminDTO getAdminById(Long id) throws ProfileException;
}
