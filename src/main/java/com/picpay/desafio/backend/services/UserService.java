package com.picpay.desafio.backend.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.picpay.desafio.backend.domain.entity.user.User;
import com.picpay.desafio.backend.exceptions.UserNotFoundException;
import com.picpay.desafio.backend.repositories.UserRepository;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(String stringfiedId) {
        return this.getUserById(Long.parseLong(stringfiedId));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    
}
