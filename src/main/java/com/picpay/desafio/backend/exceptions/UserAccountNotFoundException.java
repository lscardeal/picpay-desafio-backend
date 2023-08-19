package com.picpay.desafio.backend.exceptions;

public class UserAccountNotFoundException extends RuntimeException {
    public UserAccountNotFoundException(Long id) {
        super("User Account not found for the id " + id);
    }
}
