package com.picpay.desafio.backend.domain.entity.user;

public enum UserType {

    REGULAR, MERCHANT;
    
    public boolean isMerchant() {
        return this == MERCHANT;
    }
}