package com.picpay.desafio.backend.external.gateways;

import org.springframework.stereotype.Component;

import com.picpay.desafio.backend.external.clients.TransactionNotifierClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransactionNotifierGateway {
    
    private final TransactionNotifierClient transactionNotifierClient;

    public void notifyTransaction() {
        this.transactionNotifierClient.notifyTransaction();
    }
}
