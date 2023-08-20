package com.picpay.desafio.backend.adapters;

import com.picpay.desafio.backend.domain.values.TransferenceAuthorization;

public class AuthorizationAdapter {

    private static final String AUTORIZADO = "Autorizado";

    public static TransferenceAuthorization fromMessageToAuthorization(String message) {
        if (message.equals(AUTORIZADO))
            return TransferenceAuthorization.AUTHORIZED;
        return TransferenceAuthorization.DENIED;
    }
    
}
