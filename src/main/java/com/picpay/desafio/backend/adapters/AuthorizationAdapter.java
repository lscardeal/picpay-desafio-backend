package com.picpay.desafio.backend.adapters;

import com.picpay.desafio.backend.domain.values.TransactionAuthorization;

public class AuthorizationAdapter {

    private static final String AUTORIZADO = "Autorizado";

    public static TransactionAuthorization fromMessageToAuthorization(String message) {
        if (message.equals(AUTORIZADO))
            return TransactionAuthorization.AUTHORIZED;
        return TransactionAuthorization.DENIED;
    }
    
}
