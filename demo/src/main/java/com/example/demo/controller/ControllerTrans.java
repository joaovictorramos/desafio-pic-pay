package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configuration.ValidationTransfer;
import com.example.demo.email.services.ServiceAlterPassword;
import com.example.demo.exceptions.BusinessException;
//import com.example.demo.email.services.ServiceEmail;
import com.example.demo.exceptions.DataNotValidException;
import com.example.demo.model.CommunPerson;
import com.example.demo.model.StoreKeeper;
import com.example.demo.service.ServiceCommunPerson;
import com.example.demo.service.ServiceStoreKeeper;

//import jakarta.mail.Store;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/transfer")
public class ControllerTrans {
    @Autowired
    private ServiceCommunPerson serviceCommunPerson;

    @Autowired
    private ServiceStoreKeeper serviceStoreKeeper;

    //@Autowired
    //private ServiceEmail serviceEmail;

    @Autowired
    private ServiceAlterPassword serviceAlterPassword;
  

    @PostMapping("/{cpf_sender}/{cpf_recipient}/{value}")
    public ResponseEntity<?> transfer(@PathVariable("cpf_sender") @Valid String cpf_sender, @PathVariable("cpf_recipient") @Valid String cpf_recipient, @PathVariable("value") @Valid String value) throws DataNotValidException, BusinessException{
        Optional<CommunPerson> optionalCPS = serviceCommunPerson.findByCpf(cpf_sender);

        if(optionalCPS.isEmpty()){
            return ResponseEntity.badRequest().body("Cpf sender not found! \n");
        }

        Optional<CommunPerson> optionalCPR = serviceCommunPerson.findByCpf(cpf_recipient);
        Optional<StoreKeeper> optionalSKR = serviceStoreKeeper.findByCpf(cpf_recipient);

        Boolean statusValidationTransfer = ValidationTransfer.setRecipient(optionalCPS, optionalCPR, optionalSKR, Double.valueOf(value), serviceCommunPerson, serviceStoreKeeper);
        
        if(!statusValidationTransfer){
            return ResponseEntity.badRequest().body("Cpf recipient not found! \n");
        }

        serviceCommunPerson.save(optionalCPS.get());
        serviceAlterPassword.updateProperty(optionalCPS.get().getPassword());

        return ResponseEntity.status(HttpStatus.OK).body("Complet transfer sucessfully!");

        
        /*
         * Para envio de mensagem para o e-mail do destinatário
         * 
         */
        //System.out.println(serviceAlterPassword.senderEmailPassword());

        //String title = "Transfer received";
        //String content = "You received R$" +  value.toString()  + " from " + optionalCPS.get().getEmail() + " with sucessfully";
        //serviceEmail.sendEmail(optionalCPS.get().getEmail(), optionalSKR.get().getEmail(), title, content);
    }
}
