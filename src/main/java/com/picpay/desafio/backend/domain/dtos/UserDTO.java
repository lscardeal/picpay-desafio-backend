package com.picpay.desafio.backend.domain.dtos;

import com.picpay.desafio.backend.domain.values.UserType;

public record UserDTO(String name, String surname, String document, String email, String password, UserType userType) {

}