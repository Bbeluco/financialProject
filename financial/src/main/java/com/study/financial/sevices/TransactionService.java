package com.study.financial.sevices;

import com.study.financial.DTO.TransactionDTO;
import com.study.financial.constants.RegexConstants;
import com.study.financial.entities.UserEntity;
import com.study.financial.exceptions.*;
import com.study.financial.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class TransactionService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpRequestAuthTransferBalanceService httpRequestAuthTransferBalanceService;

    private UserEntity payer;
    private UserEntity payee;

    @Transactional // TODO it is recommended to not use this TAG and do the procedure without framework help
    public boolean transaction(TransactionDTO transactionDTO) throws Exception {
        getUserFromDatabase(transactionDTO);
        validationsBeforeTransaction(transactionDTO, this.payer);
        setNewBalanceValuesToPayerAndPayee(transactionDTO);

        isAuthorizedToTransfer();

        saveNewBalanceValuesInDatabase();
        return true;
    }

    private void isAuthorizedToTransfer() throws InstableSystemRunning, ThirdPartServiceDoesNotAuthorizeTransfer {
        if(!httpRequestAuthTransferBalanceService.isTransferAuthorizedByThirdPartApp()){
            throw new ThirdPartServiceDoesNotAuthorizeTransfer("Sorry, the third part system does not authorize you to complete the transaction");
        }
    }

    private void setNewBalanceValuesToPayerAndPayee(TransactionDTO transactionDTO) {
        double newPayerBalance = calculateNewBalance("payer", transactionDTO);
        double newPayeeBalance = calculateNewBalance("payee", transactionDTO);

        this.payer.getWallet().setBalance((float) newPayerBalance);
        this.payee.getWallet().setBalance((float) newPayeeBalance);
    }

    private void saveNewBalanceValuesInDatabase() {
        this.userRepository.save(this.payer);
        this.userRepository.save(this.payee);
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
