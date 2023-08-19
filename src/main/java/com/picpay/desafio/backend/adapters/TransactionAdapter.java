package com.picpay.desafio.backend.adapters;

import java.time.LocalDateTime;

import com.picpay.desafio.backend.domain.entity.transaction.Transaction;
import com.picpay.desafio.backend.domain.entity.user.User;
import com.picpay.desafio.backend.domain.models.TransactionModel;
import com.picpay.desafio.backend.services.UserService;

public class TransactionAdapter {
    
    public Transaction fromModelToEntity(TransactionModel transactionModel, UserService userService) {
        
            User sender = userService.getUserById(transactionModel.getSender());
            User receiver = userService.getUserById(transactionModel.getReceiver());;

            return Transaction.builder()
                            .senderAccount(sender.getUserAccount())
                            .receiverAccount(receiver.getUserAccount())
                            .value(transactionModel.getValue())
                            .timestamp(LocalDateTime.now())
                            .build();
    }
}