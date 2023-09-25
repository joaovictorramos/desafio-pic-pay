package com.example.demo.configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("com.example.demo.model")
public class ValidationLogic {

    public static Boolean validationEmail(String email){
        if(email == null){
            return false;
        }

        Pattern pattern = Pattern.compile("^[^\\s@]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)+$");
        Matcher matcher = pattern.matcher(email);

        if(!matcher.matches()){
            return false;
        }
        return true;
    }

    public static Boolean validationCpf(String cpf){
        if(cpf == null){
            return false;
        }

        Pattern pattern = Pattern.compile("([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})");
        Matcher matcher = pattern.matcher(cpf);

        if(!matcher.matches()){
            return false;
        }
        return true;
    }
}
