package com.study.financial.sevices;

import com.study.financial.DTO.TransactionDTO;
import com.study.financial.constants.RegexConstants;
import com.study.financial.entities.UserEntity;
import com.study.financial.exceptions.NotEnoughMoneyException;
import com.study.financial.exceptions.SellerPayingException;
import com.study.financial.exceptions.UserNotFoundException;
import com.study.financial.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {
    @Autowired
    private UserRepository userRepository;

    private UserEntity payer;
    private UserEntity payee;

    @Transactional // TODO it is recommended to not use this TAG and do the procedure without framework help
    public boolean transaction(TransactionDTO transactionDTO) throws Exception {
        getUserFromDatabase(transactionDTO);
        validationsBeforeTransaction(transactionDTO, this.payer);
        double newPayerBalance = calculateNewBalance("payer", transactionDTO);
        double newPayeeBalance = calculateNewBalance("payee", transactionDTO);

        this.payer.getWallet().setBalance((float) newPayerBalance);
        this.payee.getWallet().setBalance((float) newPayeeBalance);


        this.userRepository.save(this.payer);
        this.userRepository.save(this.payee);
        return true;
    }

    private double calculateNewBalance(String entityInvolved, TransactionDTO transactionDTO) {
        if(entityInvolved.equals("payer"))
            return this.payer.getWallet().getBalance() - transactionDTO.getValue();

        return this.payee.getWallet().getBalance() + transactionDTO.getValue();
    }

    private void getUserFromDatabase(TransactionDTO transactionDTO) throws UserNotFoundException {
        try{
            this.payer = userRepository.findById(transactionDTO.getPayer()).get();
            this.payee = userRepository.findById(transactionDTO.getPayee()).get();
        } catch (Exception e){
            throw new UserNotFoundException("PAYER or PAYEE not found");
        }
    }

    private void validationsBeforeTransaction(TransactionDTO transactionDTO, UserEntity payer) throws SellerPayingException, NotEnoughMoneyException {
        isPayerASeller(payer.getCpf());
        isCashAvailable(transactionDTO.getValue(), payer.getWallet().getBalance());
    }

    private void isPayerASeller(String cpf) throws SellerPayingException {
        if(!cpf.matches(RegexConstants.CPF_REGEXP)){
            throw new SellerPayingException("Seller should not transfer their money");
        }
    }

    private void isCashAvailable(double value, double userBalance) throws NotEnoughMoneyException {
        if(userBalance < value){
            throw new NotEnoughMoneyException("The payer choosen has not enough money to complete the transaction");
        }
    }
}
