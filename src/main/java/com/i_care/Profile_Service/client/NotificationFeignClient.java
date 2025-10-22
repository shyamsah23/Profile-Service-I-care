package com.i_care.Profile_Service.client;

import com.i_care.Profile_Service.ProfileServiceApplication;
import com.i_care.Profile_Service.dto.EmailWithHtmlDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Notification-Service", url = "${NOTIFICATION_SERVICE_URL}")
public interface NotificationFeignClient {


    @PostMapping("/api/mail/htmlMail")
    public void sendMailWithHTML(@RequestBody EmailWithHtmlDTO emailWithHtmlDTO);

}
