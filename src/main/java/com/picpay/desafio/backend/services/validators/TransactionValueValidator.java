package com.picpay.desafio.backend.services.validators;

import java.math.BigDecimal;

import com.picpay.desafio.backend.domain.entity.transaction.Transaction;
import com.picpay.desafio.backend.exceptions.OutOfBalanceException;

public class TransactionValueValidator {
    
    public void validate(Transaction transaction) {
        this.validate(transaction.getSenderAccount().getBalance(), transaction.getValue());
    }

    public void validate(BigDecimal balance, BigDecimal value) throws OutOfBalanceException {
        if (value.compareTo(balance) == 1)
            throw new OutOfBalanceException();
    }
}
