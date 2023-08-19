package com.picpay.desafio.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.desafio.backend.domain.dtos.UserDTO;
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

    public User createUser(final UserDTO userDTO) {
        User user = this.assembleUser(userDTO);

        UserAccount userAccount = this.userAccountService.createUserAccount(user);
        user.setUserAccount(userAccount);

        this.userRepository.save(user);
        return user;
    }

    private User assembleUser(final UserDTO userDTO) {
        return User.builder()
                .name(userDTO.name())
                .surname(userDTO.surname())
                .document(userDTO.document())
                .email(userDTO.email())
                .password(userDTO.password())
                .userType(userDTO.userType())
                .build();
    }
    
}
