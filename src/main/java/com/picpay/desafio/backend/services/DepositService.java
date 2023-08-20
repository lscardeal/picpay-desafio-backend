package com.picpay.desafio.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.desafio.backend.domain.dtos.transaction.DepositDTO;
import com.picpay.desafio.backend.domain.entity.transaction.Deposit;
import com.picpay.desafio.backend.domain.entity.user.UserAccount;

@Service
public class DepositService {

    @Autowired
    private UserAccountService userAccountService;
    
    public Deposit deposit(final DepositDTO depositDTO) {
        Deposit deposit = assembleDeposit(depositDTO);
        userAccountService.updateDeposit(deposit);
        return deposit;
    }

    public Deposit assembleDeposit(final DepositDTO depositDTO) {
        UserAccount receiver = userAccountService.getUserAccountById(depositDTO.receiverId());
        return new Deposit(receiver, depositDTO.value());
    }
}
