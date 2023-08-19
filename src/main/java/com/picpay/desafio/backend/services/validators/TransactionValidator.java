package com.picpay.desafio.backend.services.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.desafio.backend.domain.entity.transaction.Transaction;

@Service
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
