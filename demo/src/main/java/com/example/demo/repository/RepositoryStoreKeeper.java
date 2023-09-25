package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.StoreKeeper;

public interface RepositoryStoreKeeper extends JpaRepository<StoreKeeper, String>{
    Optional<StoreKeeper> findByCpf(String cpf);
    Optional<StoreKeeper> findByEmailOrCpf(String email, String cpf);
}
