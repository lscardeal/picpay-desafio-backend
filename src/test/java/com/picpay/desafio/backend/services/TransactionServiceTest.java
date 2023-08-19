package com.picpay.desafio.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.picpay.desafio.backend.domain.dtos.TransactionDTO;
import com.picpay.desafio.backend.domain.entity.transaction.Transaction;
import com.picpay.desafio.backend.domain.entity.user.UserAccount;
import com.picpay.desafio.backend.domain.values.TransactionAuthorization;
import com.picpay.desafio.backend.exceptions.TransactionDeniedException;
import com.picpay.desafio.backend.external.gateways.TransactionAuthorizerGateway;
import com.picpay.desafio.backend.external.gateways.TransactionNotifierGateway;
import com.picpay.desafio.backend.repositories.TransactionRepository;
import com.picpay.desafio.backend.services.validators.TransactionValidator;

public class TransactionServiceTest {

    @Mock
    private TransactionValidator transactionValidator;

    @Mock
    private TransactionRepository repository;

    @Mock
    private UserAccountService userAccountService;

    @Mock
    private TransactionAuthorizerGateway authorizerGateway;

    @Mock
    private TransactionNotifierGateway notifierGateway;

    @InjectMocks
    private TransactionService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void checkAuthorizationError() {
        TransactionAuthorization auth = TransactionAuthorization.DENIED;
        when(authorizerGateway.getAuthorization()).thenReturn(auth);
        assertThrows(TransactionDeniedException.class, () -> service.checkAuthorization());
    }

    @Test
    void createTransaction() {
        Long senderId = 1L;
        Long receiverId = 2L;
        BigDecimal value = BigDecimal.TEN;
        TransactionDTO dto = new TransactionDTO(senderId, receiverId, value);

        UserAccount sender = UserAccount.builder().id(senderId).build();
        UserAccount receiver = UserAccount.builder().id(receiverId).build();
        
        when(userAccountService.getUserAccountById(senderId)).thenReturn(sender);
        when(userAccountService.getUserAccountById(receiverId)).thenReturn(receiver);
        when(authorizerGateway.getAuthorization()).thenReturn(TransactionAuthorization.AUTHORIZED);

        Transaction transaction = service.createTransaction(dto);

        assertEquals(sender, transaction.getSenderAccount());
        assertEquals(receiver, transaction.getReceiverAccount());
        assertEquals(value, transaction.getValue());
        assertTrue(transaction.getTimestamp() instanceof LocalDateTime);

        verify(transactionValidator).validate(any(Transaction.class));
        verify(authorizerGateway).getAuthorization();
        verify(repository).save(any(Transaction.class));
        verify(userAccountService).updateTransactionUserAccounts(any(Transaction.class));
        verify(notifierGateway).notifyTransaction();
    }
}
