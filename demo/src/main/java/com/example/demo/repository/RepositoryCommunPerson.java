package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CommunPerson;

public interface RepositoryCommunPerson extends JpaRepository<CommunPerson, String>{

    Optional<CommunPerson> findByCpf(String cpf);
    Optional<CommunPerson> findByEmailOrCpf(String email, String cpf);
}
