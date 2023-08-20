package com.picpay.desafio.backend.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.desafio.backend.domain.entity.transaction.Transaction;
import com.picpay.desafio.backend.domain.entity.user.User;
import com.picpay.desafio.backend.domain.entity.user.UserAccount;
import com.picpay.desafio.backend.exceptions.UserAccountNotFoundException;
import com.picpay.desafio.backend.repositories.UserAccountRepository;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public void updateTransactionUserAccounts(final Transaction transaction) {
        this.updateSenderAccount(transaction);
        this.updateReceiverAccount(transaction);
    }

    private void updateSenderAccount(final Transaction transaction) {
        UserAccount senderAccount = transaction.getSenderAccount();
        senderAccount.getTransactions().add(transaction);
        senderAccount.setBalance(senderAccount.getBalance().subtract(transaction.getValue()));
        userAccountRepository.save(senderAccount);
    }

    private void updateReceiverAccount(final Transaction transaction) {
        UserAccount receiverAccount = transaction.getReceiverAccount();
        receiverAccount.getTransactions().add(transaction);
        receiverAccount.setBalance(receiverAccount.getBalance().add(transaction.getValue()));
        userAccountRepository.save(receiverAccount);
    }

    public UserAccount createUserAccount(final User user) {
        UserAccount userAccount = this.assembleUserAccount(user);
        userAccountRepository.save(userAccount);
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
