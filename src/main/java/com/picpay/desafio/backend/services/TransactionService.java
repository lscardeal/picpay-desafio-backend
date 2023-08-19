package com.picpay.desafio.backend.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.desafio.backend.domain.dtos.TransactionDTO;
import com.picpay.desafio.backend.domain.entity.transaction.Transaction;
import com.picpay.desafio.backend.domain.entity.user.User;
import com.picpay.desafio.backend.domain.entity.user.UserAccount;
import com.picpay.desafio.backend.domain.values.TransactionAuthorization;
import com.picpay.desafio.backend.exceptions.TransactionDeniedException;
import com.picpay.desafio.backend.external.gateways.TransactionAuthorizerGateway;
import com.picpay.desafio.backend.external.gateways.TransactionNotifierGateway;
import com.picpay.desafio.backend.repositories.TransactionRepository;
import com.picpay.desafio.backend.services.validators.TransactionValidator;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionValidator transactionValidator;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private TransactionAuthorizerGateway authorizerGateway;

    @Autowired
    private TransactionNotifierGateway notifierGateway;

    public Transaction createTransaction(final TransactionDTO transactionDTO) {
        Transaction transaction = this.assembleTransaction(transactionDTO);
        this.transactionValidator.validate(transaction);

        this.checkAuthorization();

        transactionRepository.save(transaction);
        userAccountService.updateTransactionUserAccounts(transaction);
        notifierGateway.notifyTransaction();

        return transaction;
    }

    public void checkAuthorization() throws TransactionDeniedException {
        TransactionAuthorization authorization = this.authorizerGateway.getAuthorization();

        if (authorization.isDenied())
            throw new TransactionDeniedException();
    }

    private Transaction assembleTransaction(final TransactionDTO transactionDTO) {
        UserAccount sender = userAccountService.getUserAccountById(transactionDTO.senderId());
        UserAccount receiver = userAccountService.getUserAccountById(transactionDTO.receiverId());;

        return Transaction.builder()
                        .senderAccount(sender)
                        .receiverAccount(receiver)
                        .value(transactionDTO.value())
                        .timestamp(LocalDateTime.now())
                        .build();
    }
}