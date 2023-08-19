package com.picpay.desafio.backend.exceptions;

public class UnsupportedUserTypeExecption extends RuntimeException {
    public UnsupportedUserTypeExecption(String type) {
        super("This action is not supported for the user type " + type);
    }
}
