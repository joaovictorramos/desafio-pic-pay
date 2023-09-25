package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.configuration.ValidationLogic;

@Configuration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerRegisterTest {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Test
    public void testSucessJsonRegisterCommun() throws JSONException{
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject userJson = new JSONObject();
        userJson.put("fullname", "joão");
        userJson.put("cpf", 11122233345F);
        userJson.put("email", "joao@gmail.com");
        userJson.put("password", 123456);

        HttpEntity<String> entity = new HttpEntity<>(userJson.toString(), headers);
        ResponseEntity<String> response = restTemplate().exchange(
            "http://localhost:8080/register-commun",
            HttpMethod.POST,
            entity,
            String.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }


    @Test
    public void testErrorJsonRegisterCustomer() throws JSONException{
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject userJson = new JSONObject();
        userJson.put("full_name", "joão"); /* full_name em vez de fullname */
        userJson.put("cpf", 11122233345F);
        userJson.put("email", "joao@gmail.com");
        userJson.put("password", 123456);
        //userJson.put("title", "error");

        HttpEntity<String> entity = new HttpEntity<>(userJson.toString(), headers);
        ResponseEntity<String> response = restTemplate().exchange(
            "http://localhost:8080/register-commun",
            HttpMethod.POST,
            entity,
            String.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }


    @Test
    public void testSucessValidationEmail(){
        Boolean email1 = ValidationLogic.validationEmail("joao@gmail.com");
        Boolean email2 = ValidationLogic.validationEmail("joao12@outlook.com.br");
        Boolean email3 = ValidationLogic.validationEmail("joao@cefet.edu.br");

        assertTrue(email1);
        assertTrue(email2);
        assertTrue(email3);
    }


    @Test
    public void testErrorValidationEmail(){
        //Boolean email1 = ValidationLogic.validationEmail("joaogmail.com");
    }


    @Test
    public void testSucessValidationCpf(){        
        Boolean cpf1 = ValidationLogic.validationCpf("111.222.333-45");
        Boolean cpf2 = ValidationLogic.validationCpf("111222333-45");
        Boolean cpf3 = ValidationLogic.validationCpf("111.22233345");
        Boolean cpf4 = ValidationLogic.validationCpf(String.valueOf(11122233345L));

        assertTrue(cpf1);
        assertTrue(cpf2);
        assertTrue(cpf3);
        assertTrue(cpf4);
    }

    @Test
    public void testErrorValidationCpf(){
        Boolean cpf1 = ValidationLogic.validationCpf("111222333.45");
        Boolean cpf2 = ValidationLogic.validationCpf("");
        
        assertTrue(cpf1);
        assertTrue(cpf2);
    }


    @Test
    public void testSucessJsonRegisterStoreKeeper() throws JSONException{
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject userJson = new JSONObject();
        userJson.put("fullname", "marco");
        userJson.put("cpf", 22233344456L);
        userJson.put("email", "marco@gmail.com");
        userJson.put("password", 1234);

        HttpEntity<String> entity = new HttpEntity<>(userJson.toString(), headers);
        ResponseEntity<String> response = restTemplate().exchange(
            "http://localhost:8080/register-storekeeper",
            HttpMethod.POST,
            entity,
            String.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }


    @Test   /* irá funcionar values de formato string */
    public void testErroJsonRegisterStoreKeeper() throws JSONException{
        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject userJson = new JSONObject();
        userJson.put("fullname", "marco");
        userJson.put("cpf", "3334445556L");
        userJson.put("email", 1.0); // email como decimal
        userJson.put("password", "bomdia");

        HttpEntity<String> entity = new HttpEntity<>(userJson.toString(), headers);
        ResponseEntity<String> response = restTemplate().exchange(
            "http://localhost:8080/register-storekeeper",
            HttpMethod.POST,
            entity,
            String.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
