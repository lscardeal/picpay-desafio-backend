package com.picpay.desafio.backend.services.validators;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.picpay.desafio.backend.domain.entity.transaction.Transference;
import com.picpay.desafio.backend.exceptions.OutOfBalanceException;

@Service
public class TransferenceValueValidator {
    
    public void validate(Transference transference) {
        this.validate(transference.getSenderAccount().getBalance(), transference.getValue());
    }

    public void validate(BigDecimal balance, BigDecimal value) throws OutOfBalanceException {
        if (value.compareTo(balance) == 1)
            throw new OutOfBalanceException();
    }
}
