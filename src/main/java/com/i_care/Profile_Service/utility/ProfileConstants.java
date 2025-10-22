package com.i_care.Profile_Service.utility;

import com.i_care.Profile_Service.exception.ProfileException;

public class ProfileConstants {

    private ProfileConstants() {
        throw new ProfileException("This is a Constants Class");
    }

    public static final String DOCTOR_ALREADY_EXISTS = "Doctor already exists.";
    public static final String PATIENT_ALREADY_EXISTS = "Patient already exists.";
    public static final String DOCTOR_NOT_FOUND = "Doctor not found.";
    public static final String PATIENT_NOT_FOUND = "Patient not found.";

}
