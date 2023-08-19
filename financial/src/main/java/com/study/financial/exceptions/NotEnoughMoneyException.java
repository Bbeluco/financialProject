package com.study.financial.exceptions;

public class NotEnoughMoneyException extends Throwable{
    private String message = "The payer choosen has not enough money to complete the transaction";

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
