package com.picpay.desafio.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.desafio.backend.domain.dtos.transaction.DepositDTO;
import com.picpay.desafio.backend.domain.entity.transaction.Deposit;
import com.picpay.desafio.backend.services.DepositService;

@RestController
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    private DepositService depositService;
    
    @PostMapping("/deposit")
    public ResponseEntity<Deposit> deposit(@RequestBody final DepositDTO depositDTO) {
        Deposit deposit = depositService.deposit(depositDTO);
        return new ResponseEntity<Deposit>(deposit, HttpStatus.OK);
    }
}
