package com.study.financial.handlers;

import com.study.financial.DTO.errors.GenericTransactionsDTO;
import com.study.financial.exceptions.IncompleteBodyParametersException;
import com.study.financial.exceptions.NotEnoughMoneyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class TransactionsErrorHandler {

    @ExceptionHandler(IncompleteBodyParametersException.class)
    @ResponseBody
    public ResponseEntity<GenericTransactionsDTO> handlerIncompleteBodyParametersException(IncompleteBodyParametersException exception){
        GenericTransactionsDTO erro = new GenericTransactionsDTO(exception.getMessage());

        return ResponseEntity.status(400).body(erro);
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    @ResponseBody
    public ResponseEntity<GenericTransactionsDTO> handlerNotEnoughMoneyException(NotEnoughMoneyException exception){
        GenericTransactionsDTO erro = new GenericTransactionsDTO(exception.getMessage());

        return ResponseEntity.status(400).body(erro);
    }


}
