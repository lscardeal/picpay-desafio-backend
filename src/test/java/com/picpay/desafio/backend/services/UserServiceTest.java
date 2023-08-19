package com.picpay.desafio.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.picpay.desafio.backend.domain.dtos.UserDTO;
import com.picpay.desafio.backend.domain.entity.user.User;
import com.picpay.desafio.backend.domain.entity.user.UserAccount;
import com.picpay.desafio.backend.domain.values.UserType;
import com.picpay.desafio.backend.exceptions.UserNotFoundException;
import com.picpay.desafio.backend.repositories.UserRepository;

public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserAccountService userAccountService;

    @InjectMocks
    private UserService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserById() {
        Long id = 1L;
        User expectedUser = User.builder().id(id).build();

        when(repository.findById(id)).thenReturn(Optional.of(expectedUser));
        User user = service.getUserById(id);

        assertEquals(expectedUser, user);
    }

    @Test
    void getUserByIdError() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.getUserById(id));
    }

    @Test
    void createUser() {
        String name = "name";
        String surname = "surname";
        String document = "document";
        String email = "email";
        String password = "password";
        UserType userType = UserType.REGULAR;
        UserAccount userAccount = UserAccount.builder().build();

        UserDTO dto = new UserDTO(name, surname, document, email, password, userType);

        when(userAccountService.createUserAccount(any(User.class))).thenReturn(userAccount);

        User user = service.createUser(dto);

        assertEquals(name, user.getName());
        assertEquals(surname, user.getSurname());
        assertEquals(document, user.getDocument());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(userType, user.getUserType());

        verify(repository).save(any(User.class));
        verify(userAccountService).createUserAccount(any(User.class));
    }
}