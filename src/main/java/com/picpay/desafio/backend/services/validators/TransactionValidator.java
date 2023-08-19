package com.picpay.desafio.backend.services.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.picpay.desafio.backend.domain.entity.transaction.Transaction;

public class TransactionValidator {

    @Autowired
    private TransactionValueValidator transactionValueValidator;

    @Autowired
    private SenderUserTypeValidator senderUserTypeValidator;
    
    public void validate(Transaction transaction) {
        this.transactionValueValidator.validate(transaction);
        this.senderUserTypeValidator.validate(transaction.getSenderAccount().getUser());
    }
}
