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

import com.picpay.desafio.backend.domain.dtos.transaction.TransferenceDTO;
import com.picpay.desafio.backend.domain.entity.transaction.Transference;
import com.picpay.desafio.backend.domain.entity.user.UserAccount;
import com.picpay.desafio.backend.domain.values.TransferenceAuthorization;
import com.picpay.desafio.backend.exceptions.TransferenceDeniedException;
import com.picpay.desafio.backend.external.gateways.TransferenceAuthorizerGateway;
import com.picpay.desafio.backend.repositories.TransferenceRepository;
import com.picpay.desafio.backend.services.validators.TransferenceValidator;

public class TransferenceServiceTest {

    @Mock
    private TransferenceValidator transferenceValidator;

    @Mock
    private TransferenceRepository repository;

    @Mock
    private UserAccountService userAccountService;

    @Mock
    private TransferenceAuthorizerGateway authorizerGateway;

    @InjectMocks
    private TransferenceService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void checkAuthorizationError() {
        Long senderId = 1L;
        Long receiverId = 2L;
        BigDecimal value = BigDecimal.TEN;
        TransferenceDTO dto = new TransferenceDTO(senderId, receiverId, value);

        UserAccount sender = new UserAccount();
        sender.setId(senderId);
        UserAccount receiver = new UserAccount();
        receiver.setId(receiverId);

        when(userAccountService.getUserAccountById(senderId)).thenReturn(sender);
        when(userAccountService.getUserAccountById(receiverId)).thenReturn(receiver);
        when(authorizerGateway.getAuthorization()).thenReturn(TransferenceAuthorization.DENIED);

        assertThrows(TransferenceDeniedException.class, () -> service.transfer(dto));
    }

    @Test
    void createTransference() {
        Long senderId = 1L;
        Long receiverId = 2L;
        BigDecimal value = BigDecimal.TEN;
        TransferenceDTO dto = new TransferenceDTO(senderId, receiverId, value);

        UserAccount sender = new UserAccount();
        sender.setId(senderId);
        UserAccount receiver = new UserAccount();
        receiver.setId(receiverId);
        
        when(userAccountService.getUserAccountById(senderId)).thenReturn(sender);
        when(userAccountService.getUserAccountById(receiverId)).thenReturn(receiver);
        when(authorizerGateway.getAuthorization()).thenReturn(TransferenceAuthorization.AUTHORIZED);

        Transference transference = service.transfer(dto);

        assertEquals(sender, transference.getSenderAccount());
        assertEquals(receiver, transference.getReceiverAccount());
        assertEquals(value, transference.getValue());
        assertTrue(transference.getTimestamp() instanceof LocalDateTime);

        verify(transferenceValidator).validate(any(Transference.class));
        verify(authorizerGateway).getAuthorization();
        verify(repository).save(any(Transference.class));
        verify(userAccountService).updateTransference(any(Transference.class));
    }
}
