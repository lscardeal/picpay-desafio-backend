package com.picpay.desafio.backend.external.gateways;

import org.springframework.stereotype.Component;

import com.picpay.desafio.backend.adapters.AuthorizationAdapter;
import com.picpay.desafio.backend.domain.dtos.transaction.TransferenceAuthorizationDTO;
import com.picpay.desafio.backend.domain.values.TransferenceAuthorization;
import com.picpay.desafio.backend.external.clients.TransferenceAuthorizerClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransferenceAuthorizerGateway {

    private final TransferenceAuthorizerClient authorizerClient; 
    
    public TransferenceAuthorization getAuthorization() {
        TransferenceAuthorizationDTO transferenceAuthorizationDTO = this.authorizerClient.getAuthorization();
        return AuthorizationAdapter.fromMessageToAuthorization(transferenceAuthorizationDTO.message());
    }
}
