package com.picpay.desafio.backend.services.utils;

import java.util.Optional;

import com.picpay.desafio.backend.domain.entity.user.User;
import com.picpay.desafio.backend.exceptions.UserNotFoundException;
import com.picpay.desafio.backend.repositories.UserRepository;

public class UserUtil {
    
    public static User getUserById(String stringfiedId, UserRepository repository) {
        Long id = getLongId(stringfiedId);
        return getUserById(id, repository);
    }

    public static User getUserById(Long id, UserRepository repository) throws UserNotFoundException {
        Optional<User> user = repository.findById(id);
        if (!user.isPresent()) throw new UserNotFoundException(id);
        return user.get();
    }

    private static Long getLongId(String id) {
        return Long.parseLong(id);
    }
}
