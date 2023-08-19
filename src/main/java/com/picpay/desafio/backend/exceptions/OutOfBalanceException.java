package com.picpay.desafio.backend.exceptions;

public class OutOfBalanceException extends RuntimeException {
    public OutOfBalanceException() {
        super("Transaction can't be concluded due out of balance error");
    }
}
