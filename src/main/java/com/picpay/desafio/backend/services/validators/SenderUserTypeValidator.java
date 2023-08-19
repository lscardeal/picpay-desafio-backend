package com.picpay.desafio.backend.services.validators;

import org.springframework.stereotype.Service;

import com.picpay.desafio.backend.domain.entity.user.User;
import com.picpay.desafio.backend.exceptions.UnsupportedUserTypeExecption;

@Service
public class SenderUserTypeValidator {
    
    public void validate(User user) throws UnsupportedUserTypeExecption {
        if (user.getUserType().isMerchant())
            throw new UnsupportedUserTypeExecption(user.getUserType().toString());
    }
}
