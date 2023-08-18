package com.study.financial.controllers;

import com.study.financial.DTO.TransactionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @PostMapping("/transaction")
    public ResponseEntity paymentTransaction(@RequestBody TransactionDTO transactionDTO){
        System.out.println(transactionDTO);
        return ResponseEntity.ok("OK");
    }
}
