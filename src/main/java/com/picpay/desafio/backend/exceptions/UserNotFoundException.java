package com.picpay.desafio.backend.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User not found for the id " + id);
    }
}
