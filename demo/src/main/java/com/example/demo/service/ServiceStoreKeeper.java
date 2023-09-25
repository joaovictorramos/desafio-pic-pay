package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.StoreKeeper;
import com.example.demo.repository.RepositoryStoreKeeper;

@Service
public class ServiceStoreKeeper {
    @Autowired
    private RepositoryStoreKeeper repositoryStoreKeeper;

    public void save(StoreKeeper storeKeeper){
        repositoryStoreKeeper.save(storeKeeper);
    }

    public Optional<StoreKeeper> findByCpf(String cpf){
        return repositoryStoreKeeper.findByCpf(cpf);
    }

    public Optional<StoreKeeper> findByEmailOrCpf(String email, String cpf){
        return repositoryStoreKeeper.findByEmailOrCpf(email, cpf);
    }
}
