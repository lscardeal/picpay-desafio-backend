package com.picpay.desafio.backend.services.validators;

import com.picpay.desafio.backend.domain.entity.transaction.Transference;
import com.picpay.desafio.backend.domain.entity.user.User;
import com.picpay.desafio.backend.domain.entity.user.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class TransferenceValidatorTest {

    @Mock
    private TransferenceValueValidator transferenceValueValidator;

    @Mock
    private SenderUserTypeValidator senderUserTypeValidator;

    @InjectMocks
    private TransferenceValidator transferenceValidator;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidate() {
        Transference transference = new Transference();
        UserAccount userAccount = new UserAccount();
        User user = new User();

        userAccount.setUser(user);
        transference.setSenderAccount(userAccount);

        transferenceValidator.validate(transference);
        verify(transferenceValueValidator).validate(transference);
        verify(senderUserTypeValidator).validate(transference.getSenderAccount().getUser());
    }
}
