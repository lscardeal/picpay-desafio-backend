package com.picpay.desafio.backend.domain.values;

public enum UserType {

    REGULAR, MERCHANT;
    
    public boolean isMerchant() {
        return this == MERCHANT;
    }
}