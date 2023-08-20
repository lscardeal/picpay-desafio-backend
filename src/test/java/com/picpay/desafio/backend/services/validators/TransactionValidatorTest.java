package com.picpay.desafio.backend.services.validators;

import com.picpay.desafio.backend.domain.entity.transaction.Transaction;
import com.picpay.desafio.backend.domain.entity.user.User;
import com.picpay.desafio.backend.domain.entity.user.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class TransactionValidatorTest {

    @Mock
    private TransactionValueValidator transactionValueValidator;

    @Mock
    private SenderUserTypeValidator senderUserTypeValidator;

    @InjectMocks
    private TransactionValidator transactionValidator;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidate() {
        Transaction transaction = new Transaction();
        UserAccount userAccount = new UserAccount();
        User user = new User();

        userAccount.setUser(user);
        transaction.setSenderAccount(userAccount);

        transactionValidator.validate(transaction);
        verify(transactionValueValidator).validate(transaction);
        verify(senderUserTypeValidator).validate(transaction.getSenderAccount().getUser());
    }
}
