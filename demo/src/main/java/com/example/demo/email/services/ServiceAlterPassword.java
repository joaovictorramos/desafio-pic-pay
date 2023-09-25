package com.example.demo.email.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.email.alterPasswords.ConfigService;

@Service
public class ServiceAlterPassword {
    @Autowired
    private ConfigService configService;

    public String senderEmailPassword(){
        return configService.getPropertyEmailPassword();
    }

    public void updateProperty(String passwordSender){
        configService.modifyProperties("spring.mail.password", passwordSender);
    }
}
