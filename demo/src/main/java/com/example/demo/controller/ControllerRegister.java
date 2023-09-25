package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configuration.ValidationLogic;
import com.example.demo.dto.CommunPersonDTO;
import com.example.demo.dto.StoreKeeperDTO;
import com.example.demo.exceptions.DataNotValidException;
import com.example.demo.model.CommunPerson;
import com.example.demo.model.StoreKeeper;
import com.example.demo.service.ServiceCommunPerson;
import com.example.demo.service.ServiceStoreKeeper;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/")
public class ControllerRegister {
    @Autowired
    private ServiceCommunPerson serviceCommunPerson;

    @Autowired
    private ServiceStoreKeeper serviceStoreKeeper;

    @PostMapping(value = "/register-commun", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerCommun(@RequestBody @Valid CommunPersonDTO communPersonDTO) throws DataNotValidException{
        CommunPerson communPerson = new CommunPerson(communPersonDTO);

        if(!ValidationLogic.validationEmail(communPerson.getEmail())){
            return ResponseEntity.badRequest().body("Email not valid!");
        }        

        if(!ValidationLogic.validationCpf(communPerson.getCpf())){
            return ResponseEntity.badRequest().body("Cpf not valid!");
        }
       
        Optional<CommunPerson> optionalCP = serviceCommunPerson.findByEmailOrCpf(communPerson.getEmail(), communPerson.getCpf());
        if(!optionalCP.isEmpty()){
            return ResponseEntity.badRequest().body("Email or Cpf already registred!");
        }

        serviceCommunPerson.save(communPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registred!");
    }


    @PostMapping(value = "/register-storekeeper", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerStoreKeeper(@RequestBody @Valid StoreKeeperDTO storeKeeperDTO) throws DataNotValidException{
        StoreKeeper storeKeeper = new StoreKeeper(storeKeeperDTO);

        if(!ValidationLogic.validationEmail(storeKeeper.getEmail())){
            return ResponseEntity.badRequest().body("Email not valid!");
        }
        
        if(!ValidationLogic.validationCpf(storeKeeper.getCpf())){
            return ResponseEntity.badRequest().body("Cpf not valid!");
        }

        Optional<StoreKeeper> optionalSK = serviceStoreKeeper.findByEmailOrCpf(storeKeeper.getEmail(), storeKeeper.getCpf());
        if(!optionalSK.isEmpty()){
            return ResponseEntity.badRequest().body("Email or Cpf already registred!");
        }

        serviceStoreKeeper.save(storeKeeper);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registred!");
    }
}
