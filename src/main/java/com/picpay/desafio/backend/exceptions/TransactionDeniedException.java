package com.picpay.desafio.backend.exceptions;

public class TransactionDeniedException extends RuntimeException {
    public TransactionDeniedException() {
        super("The transaction was denied");
    }
}
