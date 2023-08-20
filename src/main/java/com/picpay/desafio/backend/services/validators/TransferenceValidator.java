package com.picpay.desafio.backend.services.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.desafio.backend.domain.entity.transaction.Transference;

@Service
public class TransferenceValidator {

    @Autowired
    private TransferenceValueValidator transferenceValueValidator;

    @Autowired
    private SenderUserTypeValidator senderUserTypeValidator;
    
    public void validate(Transference transference) {
        this.transferenceValueValidator.validate(transference);
        this.senderUserTypeValidator.validate(transference.getSenderAccount().getUser());
    }
}
