package com.picpay.desafio.backend.services.validators;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.picpay.desafio.backend.exceptions.OutOfBalanceException;

public class TransferenceValueValidatorTest {

    @Test
    void validateBalanceGreaterThanValue() {
        TransferenceValueValidator validator = new TransferenceValueValidator();
        BigDecimal balance = BigDecimal.TEN;
        BigDecimal value = BigDecimal.ONE;
        validator.validate(balance,value);
    }

    @Test
    void validateBalanceEqualsValue() {
        TransferenceValueValidator validator = new TransferenceValueValidator();
        BigDecimal balance = BigDecimal.TEN;
        BigDecimal value = BigDecimal.TEN;
        validator.validate(balance,value);
    }

    @Test
    void validateBalanceLessThanValue() {
        TransferenceValueValidator validator = new TransferenceValueValidator();
        BigDecimal balance = BigDecimal.ONE;
        BigDecimal value = BigDecimal.TEN;
        assertThrows(OutOfBalanceException.class, () -> validator.validate(balance,value));
    }
}
