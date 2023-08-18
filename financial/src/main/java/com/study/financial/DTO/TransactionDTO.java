package com.study.financial.DTO;

import com.study.financial.exceptions.IncompleteBodyParametersException;

public class TransactionDTO {

    private double value;
    private int payer;
    private int payee;

    public TransactionDTO(double value, int payer, int payee) throws IncompleteBodyParametersException {
        requestMethodsValidation(payer, payee);
        this.value = value;
        this.payer = payer;
        this.payee = payee;
    }

    private static void requestMethodsValidation(int payer, int payee) throws IncompleteBodyParametersException  {
        if(payer == 0 && payee == 0){
            throw new IncompleteBodyParametersException("Please, selece a valid payer and payee");
        }

        if(payer == 0){
            throw new IncompleteBodyParametersException("Please, selece a valid payer");
        }

        if(payee == 0) {
            throw new IncompleteBodyParametersException("Please, selece a valid payee");
        }
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getPayer() {
        return payer;
    }

    public void setPayer(int payer) {
        this.payer = payer;
    }

    public int getPayee() {
        return payee;
    }

    public void setPayee(int payee) {
        this.payee = payee;
    }
}
