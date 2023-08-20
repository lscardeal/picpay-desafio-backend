package com.picpay.desafio.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.picpay.desafio.backend.domain.dtos.transaction.DepositDTO;
import com.picpay.desafio.backend.domain.entity.transaction.Deposit;
import com.picpay.desafio.backend.domain.entity.user.UserAccount;

public class DepositServiceTest {

    @Mock
    private UserAccountService userAccountService;

    @InjectMocks
    private DepositService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deposit() {
        Long receiverId = 1L;
        BigDecimal value = BigDecimal.TEN;
        DepositDTO dto = new DepositDTO(receiverId, value);

        UserAccount receiver = new UserAccount();
        receiver.setId(receiverId);

        when(userAccountService.getUserAccountById(receiverId)).thenReturn(receiver);

        Deposit deposit = service.deposit(dto);

        assertEquals(receiver, deposit.getReceiverAccount());
        assertEquals(value, deposit.getValue());
        assertTrue(deposit.getTimestamp() instanceof LocalDateTime);
    }
    
}
