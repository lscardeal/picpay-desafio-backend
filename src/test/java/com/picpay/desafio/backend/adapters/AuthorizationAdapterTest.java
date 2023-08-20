package com.picpay.desafio.backend.adapters;

import com.picpay.desafio.backend.domain.values.TransferenceAuthorization;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorizationAdapterTest {

    @Test
    void authorizedMessageConversion() {
        String authorizedMessage = "Autorizado";
        TransferenceAuthorization result = AuthorizationAdapter.fromMessageToAuthorization(authorizedMessage);
        assertEquals(TransferenceAuthorization.AUTHORIZED, result);
    }

    @Test
    void deniedMessageConversion() {
        String deniedMessage = "SomeOtherStatus";
        TransferenceAuthorization result = AuthorizationAdapter.fromMessageToAuthorization(deniedMessage);
        assertEquals(TransferenceAuthorization.DENIED, result);
    }
}
