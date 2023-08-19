package com.picpay.desafio.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.desafio.backend.adapters.TransactionAdapter;
import com.picpay.desafio.backend.domain.entity.transaction.Transaction;
import com.picpay.desafio.backend.domain.entity.user.User;
import com.picpay.desafio.backend.domain.models.TransactionModel;
import com.picpay.desafio.backend.exceptions.UserNotFoundException;
import com.picpay.desafio.backend.repositories.TransactionRepository;
import com.picpay.desafio.backend.repositories.UserRepository;
import com.picpay.desafio.backend.services.utils.UserUtil;
import com.picpay.desafio.backend.services.validators.TransactionValidator;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionValidator transactionValidator;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionAdapter transactionAdapter;

    @Autowired
    private UserService userService;

    public void createTransaction(TransactionModel transactionModel) {
        Transaction transaction = this.transactionAdapter.fromModelToEntity(transactionModel, userService);
        this.transactionValidator.validate(transaction);
        transactionRepository.save(transaction);
    }
}