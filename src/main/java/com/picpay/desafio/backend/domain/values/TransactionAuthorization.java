package com.picpay.desafio.backend.domain.values;

public enum TransactionAuthorization {

    AUTHORIZED, DENIED;

    public boolean isAuthorized() {
        return this == AUTHORIZED;
    }

    public boolean isDenied() {
        return this == DENIED;
    }
    
}
