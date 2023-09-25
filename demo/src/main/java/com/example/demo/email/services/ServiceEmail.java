package com.example.demo.email.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j

@Service
public class ServiceEmail {
    @Autowired
    private final JavaMailSender mailSender;
    private final String sender;
    
    public static org.slf4j.Logger getLog() {
        return log;
    }

    public ServiceEmail(@Value("${spring.mail.username}")String sender, final JavaMailSender mailSender){
        this.sender = sender;
        this.mailSender = mailSender;
    }

    public void sendEmail(String sender, String recipient, String title, String content){
        log.info("Sending email...");

        var message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(recipient);
        message.setSubject(title);
        message.setText(content);

        System.out.println(sender);

        mailSender.send(message);
        log.info("Sended message sucessfully!");
    }
}
