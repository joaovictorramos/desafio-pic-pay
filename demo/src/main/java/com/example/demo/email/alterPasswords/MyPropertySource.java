package com.example.demo.email.alterPasswords;

import org.springframework.core.env.PropertySource;

public class MyPropertySource extends PropertySource<Object>{
    private final String propertyName;
    private final String passwordSender;

    public MyPropertySource(String propertyName, String passwordSender){
        super("myPropertySource");
        this.propertyName = propertyName;
        this.passwordSender = passwordSender;
    }

    @Override
    public Object getProperty(String propertyOrigin) {
        if(propertyName.equals(propertyOrigin)){
            return passwordSender;
        }

        return null;
    }
}
