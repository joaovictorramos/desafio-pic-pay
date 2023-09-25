package com.example.demo.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class StoreKeeperDTO {
    @NotBlank
    @NotEmpty
    public String fullname;

    @NotBlank
    @NotEmpty
    public String cpf;

    @NotBlank
    @NotEmpty
    @Email
    public String email;

    @NotBlank
    @NotEmpty
    public String password; 
}