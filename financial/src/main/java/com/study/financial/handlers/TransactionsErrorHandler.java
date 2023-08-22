package com.study.financial.handlers;

import com.study.financial.DTO.errors.GenericTransactionsDTO;
import com.study.financial.exceptions.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class TransactionsErrorHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<GenericTransactionsDTO> handlerUserNotFoundException(Exception exception){
        GenericTransactionsDTO erro = new GenericTransactionsDTO(exception.getMessage());

        return ResponseEntity.status(404).body(erro);
    }

    @ExceptionHandler({NotEnoughMoneyException.class, SellerPayingException.class,
            IncompleteBodyParametersException.class, EntityNotFoundException.class})
    @ResponseBody
    public ResponseEntity<GenericTransactionsDTO> handlerGenericExceptions(Exception exception){
        GenericTransactionsDTO erro = new GenericTransactionsDTO(exception.getMessage());

        return ResponseEntity.status(400).body(erro);
    }

    @ExceptionHandler(ThirdPartServiceDoesNotAuthorizeTransfer.class)
    @ResponseBody
    public ResponseEntity<GenericTransactionsDTO> handlerThirdPartServiceDoesNotAuthorizeTransferException(Exception exception){
        GenericTransactionsDTO erro = new GenericTransactionsDTO(exception.getMessage());

        return ResponseEntity.status(403).body(erro);
    }

    @ExceptionHandler(InstableSystemRunning.class)
    @ResponseBody
    public ResponseEntity<GenericTransactionsDTO> handlerInstableSystemRunningException(Exception exception){
        GenericTransactionsDTO erro = new GenericTransactionsDTO(exception.getMessage());

        return ResponseEntity.status(503).body(erro);
    }


}
