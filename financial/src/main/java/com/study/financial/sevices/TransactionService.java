package com.study.financial.sevices;

import com.study.financial.DTO.TransactionDTO;
import com.study.financial.entities.UserEntity;
import com.study.financial.exceptions.NotEnoughMoneyException;
import com.study.financial.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private UserRepository userRepository;

    public boolean transaction(TransactionDTO transactionDTO) throws NotEnoughMoneyException {
        UserEntity payer = userRepository.getReferenceById(transactionDTO.getPayer()); //TODO Add exception handler if user do not exist
        UserEntity payee = userRepository.getReferenceById(transactionDTO.getPayer()); //TODO Add exception handler if user do not exist

        //TODO payer must not pay if is a seller

        cashAvailableValidation(transactionDTO.getValue(), payer.getWallet().getBalance());

        return true;
    }

    private static void cashAvailableValidation(double value, double userBalance) throws NotEnoughMoneyException {
        if(userBalance < value){
            throw new NotEnoughMoneyException();
        }
    }
}
