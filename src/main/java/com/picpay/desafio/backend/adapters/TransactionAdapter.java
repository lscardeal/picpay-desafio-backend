package com.picpay.desafio.backend.adapters;

import java.time.LocalDateTime;

import com.picpay.desafio.backend.domain.entity.transaction.Transaction;
import com.picpay.desafio.backend.domain.entity.user.User;
import com.picpay.desafio.backend.domain.models.TransactionModel;
import com.picpay.desafio.backend.repositories.UserRepository;
import com.picpay.desafio.backend.services.utils.UserUtil;

public class TransactionAdapter {
    
    public Transaction fromModelToEntity(TransactionModel model, UserRepository userRepository) {
        
            User sender = UserUtil.getUserById(model.getSender(), userRepository);
            User receiver = UserUtil.getUserById(model.getReceiver(), userRepository);

            return Transaction.builder()
                            .senderAccount(sender.getUserAccount())
                            .receiverAccount(receiver.getUserAccount())
                            .value(model.getValue())
                            .timestamp(LocalDateTime.now())
                            .build();
    }
}
