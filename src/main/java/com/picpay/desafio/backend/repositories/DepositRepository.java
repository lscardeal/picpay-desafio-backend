package com.picpay.desafio.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpay.desafio.backend.domain.entity.transaction.Deposit;

public interface DepositRepository extends JpaRepository<Deposit, Long>{
    
}
