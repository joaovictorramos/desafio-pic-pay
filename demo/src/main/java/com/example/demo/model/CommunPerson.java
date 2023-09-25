package com.example.demo.model;

import com.example.demo.dto.CommunPersonDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "commun_person")
public class CommunPerson {
    @Column(name = "full_name", unique = false, nullable = false)
    private String fullName;
    
    @Id
    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", unique = false, nullable = false)
    private String password;

    @Column(name = "wallet", unique = false, nullable = false)
    private Double wallet = 500.0;

    public CommunPerson(CommunPersonDTO communPersonDTO){
        this.fullName = communPersonDTO.fullname;
        this.cpf = communPersonDTO.cpf;
        this.email = communPersonDTO.email.toLowerCase();
        this.password = communPersonDTO.password;
    }
}
