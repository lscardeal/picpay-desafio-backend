package com.picpay.desafio.backend.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.desafio.backend.domain.dtos.TransactionDTO;
import com.picpay.desafio.backend.domain.entity.transaction.Transaction;
import com.picpay.desafio.backend.domain.entity.user.User;
import com.picpay.desafio.backend.repositories.TransactionRepository;
import com.picpay.desafio.backend.services.validators.TransactionValidator;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionValidator transactionValidator;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountService userAccountService;

    public Transaction createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = this.assembleTransaction(transactionDTO);
        this.transactionValidator.validate(transaction);
        transactionRepository.save(transaction);
        userAccountService.updateTransactionUserAccounts(transaction);
        return transaction;
    }

    private Transaction assembleTransaction(TransactionDTO transactionDTO) {
        User sender = userService.getUserById(transactionDTO.senderId());
        User receiver = userService.getUserById(transactionDTO.receiverId());;

        return Transaction.builder()
                        .senderAccount(sender.getUserAccount())
                        .receiverAccount(receiver.getUserAccount())
                        .value(transactionDTO.value())
                        .timestamp(LocalDateTime.now())
                        .build();
    }
}