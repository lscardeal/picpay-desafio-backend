package com.picpay.desafio.backend.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.desafio.backend.domain.entity.transaction.Transference;
import com.picpay.desafio.backend.domain.entity.user.User;
import com.picpay.desafio.backend.domain.entity.user.UserAccount;
import com.picpay.desafio.backend.exceptions.UserAccountNotFoundException;
import com.picpay.desafio.backend.repositories.UserAccountRepository;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public void updateTransference(final Transference transference) {
        this.updateSenderAccount(transference);
        this.updateReceiverAccount(transference);
    }

    private void updateSenderAccount(final Transference transference) {
        UserAccount senderAccount = transference.getSenderAccount();
        senderAccount.getTransferencesSended().add(transference);
        senderAccount.setBalance(senderAccount.getBalance().subtract(transference.getValue()));
        userAccountRepository.save(senderAccount);
    }

    private void updateReceiverAccount(final Transference transference) {
        UserAccount receiverAccount = transference.getReceiverAccount();
        receiverAccount.getTransferencesReceived().add(transference);
        receiverAccount.setBalance(receiverAccount.getBalance().add(transference.getValue()));
        userAccountRepository.save(receiverAccount);
    }

    public UserAccount createUserAccount(final User user) {
        UserAccount userAccount = this.assembleUserAccount(user);
        return userAccount;
    }

    private UserAccount assembleUserAccount(final User user) {
        return new UserAccount(user, BigDecimal.ZERO);
    }

    public UserAccount getUserAccountById(final String stringfiedId) {
        return this.getUserAccountById(Long.parseLong(stringfiedId));
    }

    public UserAccount getUserAccountById(final Long id) throws UserAccountNotFoundException {
        return userAccountRepository.findById(id).orElseThrow(() -> new UserAccountNotFoundException(id));
    }
    
}
