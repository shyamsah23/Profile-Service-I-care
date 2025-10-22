package com.i_care.Profile_Service.utility;

import com.i_care.Profile_Service.exception.ProfileException;

public class NotificationConstants {

    private NotificationConstants(){
        throw new ProfileException("This is Constant Class - Object Can't be created");
    }
    public static final String DOCTOR_REGISTER = "DOCTOR_REGISTER";
    public static final String PATIENT_REGISTER = "PATIENT_REGISTER";
    public static final String DOCTOR_REGISTER_SUBJECT = "Congratulations! Your i-Care Doctor Account is Ready";
    public static final String PATIENT_REGISTER_SUBJECT = "Congratulations! Your i-Care Doctor Account is Ready";
}
