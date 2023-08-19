package com.picpay.desafio.backend.adapters;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.picpay.desafio.backend.domain.dtos.TransactionDTO;
import com.picpay.desafio.backend.domain.entity.transaction.Transaction;
import com.picpay.desafio.backend.domain.entity.user.User;
import com.picpay.desafio.backend.services.UserService;

@Component
public class TransactionAdapter {
    
    public Transaction fromDTOToEntity(TransactionDTO transactionDTO, UserService userService) {

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
