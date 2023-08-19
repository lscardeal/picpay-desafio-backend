package com.picpay.desafio.backend.services.validators;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.picpay.desafio.backend.exceptions.OutOfBalanceException;
import com.picpay.desafio.backend.exceptions.UnsupportedUserTypeExecption;

public class TransactionValueValidatorTest {

    @Test
    void validateBalanceGreaterThanValue() {
        TransactionValueValidator validator = new TransactionValueValidator();
        BigDecimal balance = BigDecimal.TEN;
        BigDecimal value = BigDecimal.ONE;
        validator.validate(balance,value);
    }

    @Test
    void validateBalanceEqualsValue() {
        TransactionValueValidator validator = new TransactionValueValidator();
        BigDecimal balance = BigDecimal.TEN;
        BigDecimal value = BigDecimal.TEN;
        validator.validate(balance,value);
    }

    @Test
    void validateBalanceLessThanValue() {
        TransactionValueValidator validator = new TransactionValueValidator();
        BigDecimal balance = BigDecimal.ONE;
        BigDecimal value = BigDecimal.TEN;
        assertThrows(OutOfBalanceException.class, () -> validator.validate(balance,value));
    }
}
