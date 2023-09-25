package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CommunPerson;
import com.example.demo.repository.RepositoryCommunPerson;

@Service
public class ServiceCommunPerson {
    @Autowired
    private RepositoryCommunPerson repositoryCommunPerson;

    public void save(CommunPerson communPerson){
        repositoryCommunPerson.save(communPerson);
    }

    public Optional<CommunPerson> findByCpf(String cpf){
        return repositoryCommunPerson.findByCpf(cpf);
    }

    public Optional<CommunPerson> findByEmailOrCpf(String email, String cpf){
        return repositoryCommunPerson.findByEmailOrCpf(email, cpf);
    }
}
