package com.picpay.desafio.backend.adapters;

import com.picpay.desafio.backend.domain.values.TransactionAuthorization;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorizationAdapterTest {

    @Test
    void authorizedMessageConversion() {
        String authorizedMessage = "Autorizado";
        TransactionAuthorization result = AuthorizationAdapter.fromMessageToAuthorization(authorizedMessage);
        assertEquals(TransactionAuthorization.AUTHORIZED, result);
    }

    @Test
    void deniedMessageConversion() {
        String deniedMessage = "SomeOtherStatus";
        TransactionAuthorization result = AuthorizationAdapter.fromMessageToAuthorization(deniedMessage);
        assertEquals(TransactionAuthorization.DENIED, result);
    }
}
