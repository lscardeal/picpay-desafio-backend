package com.picpay.desafio.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.picpay.desafio.backend.domain.entity.transaction.Transaction;
import com.picpay.desafio.backend.domain.entity.user.User;
import com.picpay.desafio.backend.domain.entity.user.UserAccount;
import com.picpay.desafio.backend.repositories.UserAccountRepository;

public class UserAccountServiceTest {

    @Mock
    private UserAccountRepository repository;

    @InjectMocks
    private UserAccountService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateTransactionUserAccounts() {
        UserAccount sender = UserAccount.builder()
            .id(1L)
            .balance(BigDecimal.TEN)
            .transactions(new ArrayList<Transaction>())
            .build();
        UserAccount receiver = UserAccount.builder()
            .id(2L)
            .balance(BigDecimal.ZERO)
            .transactions(new ArrayList<Transaction>())
            .build();
        Transaction transaction = Transaction.builder()
            .senderAccount(sender)
            .receiverAccount(receiver)
            .value(BigDecimal.TEN)
            .build();

        service.updateTransactionUserAccounts(transaction);

        assertTrue(sender.getTransactions().contains(transaction));
        assertEquals(BigDecimal.ZERO, sender.getBalance());

        assertTrue(receiver.getTransactions().contains(transaction));
        assertEquals(BigDecimal.TEN, receiver.getBalance());

        verify(repository).save(sender);
        verify(repository).save(receiver);
    }
    
    @Test
    void createUserAccount() {
        User user = User.builder().id(1L).build();
        UserAccount userAccount = service.createUserAccount(user);

        assertEquals(BigDecimal.ZERO, userAccount.getBalance());
        assertEquals(user, userAccount.getUser());
        assertTrue(userAccount.getTransactions().isEmpty());

        verify(repository).save(any(UserAccount.class));
    }

    @Test
    void getUserAccountById() {
        Long id = 1L;
        UserAccount expectedUserAccount = UserAccount.builder().id(id).build();

        when(repository.findById(id)).thenReturn(Optional.of(expectedUserAccount));
        UserAccount user = service.getUserAccountById(id);

        assertEquals(expectedUserAccount, user);
    }
}
