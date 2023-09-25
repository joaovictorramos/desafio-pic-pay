package com.example.demo.configuration;

import java.util.Optional;

import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.DataNotValidException;
import com.example.demo.exceptions.ValueNotIdentifiedException;
import com.example.demo.model.CommunPerson;
import com.example.demo.model.StoreKeeper;
import com.example.demo.service.ServiceCommunPerson;
import com.example.demo.service.ServiceStoreKeeper;


public class ValidationTransfer {
    public static Boolean setRecipient(Optional<CommunPerson> optionalCPS, Optional<CommunPerson> optionalCPR, Optional<StoreKeeper> optionalSKR, Double value,
    ServiceCommunPerson serviceCommunPerson, ServiceStoreKeeper serviceStoreKeeper) throws DataNotValidException, BusinessException{
        if(optionalCPR.isEmpty() && optionalSKR.isEmpty()){
            return false;

        }else if(!optionalCPR.isEmpty()){
            if(optionalCPS.get().getCpf() == optionalCPR.get().getCpf()){
                throw new BusinessException("Cannot transfer to yourself! \n");
            }

            Double recipientValue = completTransfer(optionalCPS.get(), optionalCPR.get().getWallet(), value);
            if(recipientValue == null){
                throw new BusinessException("Sender doesn't have enough money in his wallet! \n");

            }else if(recipientValue == -1.0){
                throw new ValueNotIdentifiedException("Value not allowed! \n");
            }

            optionalCPR.get().setWallet(recipientValue);
            serviceCommunPerson.save(optionalCPR.get());


        }else if(!optionalSKR.isEmpty()){
            if(optionalCPS.get().getCpf() == optionalSKR.get().getCpf()){
                throw new DataNotValidException("Cpf recipient not found! \n");
            }

            Double recipientValue = completTransfer(optionalCPS.get(), optionalSKR.get().getWallet(), Double.valueOf(value));
            if(recipientValue == null){
                throw new BusinessException("Sender doesn't have enough money in his wallet! \n");

            }else if(recipientValue == -1.0){
                throw new ValueNotIdentifiedException("Value not allowed! \n");
            }

            optionalSKR.get().setWallet(recipientValue);
            serviceStoreKeeper.save(optionalSKR.get());
        }
        
        return true;
    }


    private static Double completTransfer(CommunPerson sender, Double recipientValue, Double value){
        if(value <= 0){
            return -1.0;
        }

        if(sender.getWallet() >= value){
            sender.setWallet(sender.getWallet() - value);
            recipientValue += value;

        }else{
            return null;
        }

        return recipientValue;
    }
}
