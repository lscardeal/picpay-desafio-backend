package com.picpay.desafio.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.desafio.backend.domain.dtos.transaction.TransferenceDTO;
import com.picpay.desafio.backend.domain.entity.transaction.Transference;
import com.picpay.desafio.backend.domain.entity.user.UserAccount;
import com.picpay.desafio.backend.domain.values.TransferenceAuthorization;
import com.picpay.desafio.backend.exceptions.TransferenceDeniedException;
import com.picpay.desafio.backend.external.gateways.TransferenceAuthorizerGateway;
import com.picpay.desafio.backend.repositories.TransferenceRepository;
import com.picpay.desafio.backend.services.validators.TransferenceValidator;

@Service
public class TransferenceService {
    
    @Autowired
    private TransferenceValidator transferenceValidator;

    @Autowired
    private TransferenceRepository transferenceRepository;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private TransferenceAuthorizerGateway authorizerGateway;

    public Transference createTransference(final TransferenceDTO transferenceDTO) {
        Transference transference = this.assembleTransference(transferenceDTO);
        this.transferenceValidator.validate(transference);

        this.checkAuthorization();

        transferenceRepository.save(transference);
        userAccountService.updateTransference(transference);

        return transference;
    }

    private void checkAuthorization() throws TransferenceDeniedException {
        TransferenceAuthorization authorization = this.authorizerGateway.getAuthorization();

        if (authorization.isDenied())
            throw new TransferenceDeniedException();
    }

    private Transference assembleTransference(final TransferenceDTO transferenceDTO) {
        UserAccount sender = userAccountService.getUserAccountById(transferenceDTO.senderId());
        UserAccount receiver = userAccountService.getUserAccountById(transferenceDTO.receiverId());;

        return new Transference(sender, receiver, transferenceDTO.value());
    }
}