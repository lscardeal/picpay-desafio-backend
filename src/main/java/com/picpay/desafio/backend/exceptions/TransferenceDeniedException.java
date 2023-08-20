package com.picpay.desafio.backend.exceptions;

public class TransferenceDeniedException extends RuntimeException {
    public TransferenceDeniedException() {
        super("The transference was denied");
    }
}
