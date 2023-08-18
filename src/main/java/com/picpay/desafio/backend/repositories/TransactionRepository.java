package com.picpay.desafio.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpay.desafio.backend.domain.entity.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    Optional<Transaction> findTransactionById(Long id);

    // Optional<User> findUserBySender();

    // Optional<User> findUserByReceiver();
}
