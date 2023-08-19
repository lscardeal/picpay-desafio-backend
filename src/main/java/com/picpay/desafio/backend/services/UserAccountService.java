package com.picpay.desafio.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.desafio.backend.domain.entity.transaction.Transaction;
import com.picpay.desafio.backend.domain.entity.user.UserAccount;
import com.picpay.desafio.backend.repositories.UserAccountRepository;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    private UserAccount saveUserAccount(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
        return userAccount;
    }

    public void updateTransactionUserAccounts(Transaction transaction) {
        this.updateSenderAccount(transaction);
        this.updateReceiverAccount(transaction);
    }

    private void updateSenderAccount(Transaction transaction) {
        UserAccount senderAccount = transaction.getSenderAccount();
        senderAccount.getTransactions().add(transaction);
        senderAccount.getBalance().subtract(transaction.getValue());
        this.saveUserAccount(senderAccount);
    }

    private void updateReceiverAccount(Transaction transaction) {
        UserAccount receiverAccount = transaction.getReceiverAccount();
        receiverAccount.getTransactions().add(transaction);
        receiverAccount.getBalance().add(transaction.getValue());
        this.saveUserAccount(receiverAccount);
    }
    
}
