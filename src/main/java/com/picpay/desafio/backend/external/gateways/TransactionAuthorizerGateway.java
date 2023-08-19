package com.picpay.desafio.backend.external.gateways;

import org.springframework.stereotype.Component;

import com.picpay.desafio.backend.adapters.AuthorizationAdapter;
import com.picpay.desafio.backend.domain.dtos.TransactionAuthorizationDTO;
import com.picpay.desafio.backend.domain.values.TransactionAuthorization;
import com.picpay.desafio.backend.external.clients.TransactionAuthorizerClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransactionAuthorizerGateway {

    private final TransactionAuthorizerClient authorizerClient; 
    
    public TransactionAuthorization getAuthorization() {
        TransactionAuthorizationDTO transactionAuthorizationDTO = this.authorizerClient.getAuthorization();
        return AuthorizationAdapter.fromMessageToAuthorization(transactionAuthorizationDTO.message());
    }
}
