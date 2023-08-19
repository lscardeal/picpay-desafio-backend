package com.picpay.desafio.backend.services.validators;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.picpay.desafio.backend.domain.entity.user.User;
import com.picpay.desafio.backend.domain.values.UserType;
import com.picpay.desafio.backend.exceptions.UnsupportedUserTypeExecption;

public class SenderUserTypeValidatorTest {

    @Test
    void validateRegularUser() {
        SenderUserTypeValidator validator = new SenderUserTypeValidator();
        User user = User.builder().userType(UserType.REGULAR).build();
        validator.validate(user);
    }

    @Test
    void validateMerchantUser() {
        SenderUserTypeValidator validator = new SenderUserTypeValidator();
        User user = User.builder().userType(UserType.MERCHANT).build();
        assertThrows(UnsupportedUserTypeExecption.class, () -> validator.validate(user));
    }
    
}
