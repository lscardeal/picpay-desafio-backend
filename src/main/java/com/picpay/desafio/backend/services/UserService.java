package com.picpay.desafio.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.desafio.backend.domain.dtos.user.UserDTO;
import com.picpay.desafio.backend.domain.entity.user.User;
import com.picpay.desafio.backend.domain.entity.user.UserAccount;
import com.picpay.desafio.backend.exceptions.UserNotFoundException;
import com.picpay.desafio.backend.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAccountService userAccountService;

    public User getUserById(final String stringfiedId) {
        return this.getUserById(Long.parseLong(stringfiedId));
    }

    public User getUserById(final Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(final UserDTO userDTO) {
        User user = this.assembleUser(userDTO);

        UserAccount userAccount = this.userAccountService.createUserAccount(user);
        user.setUserAccount(userAccount);

        this.userRepository.save(user);
        return user;
    }

    private User assembleUser(final UserDTO userDTO) {
        return new User(userDTO.name(), userDTO.surname(), userDTO.document(), userDTO.email(), userDTO.password(), userDTO.userType());
    }
    
}
