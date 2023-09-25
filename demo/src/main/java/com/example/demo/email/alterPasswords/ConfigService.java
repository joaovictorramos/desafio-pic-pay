package com.example.demo.email.alterPasswords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    @Autowired
    private final ConfigurableEnvironment environment;
    ConfigService(ConfigurableEnvironment environment) {
        this.environment = environment;
    }

    public String getPropertyEmailPassword(){
        return environment.getProperty("spring.mail.password");
    }
    
    public void modifyProperties(String propertyName, String passwordSender){
        environment.getPropertySources().addFirst(new MyPropertySource(propertyName, passwordSender));
    }
}
