package com.picpay.desafio.backend.domain.dtos;

import java.math.BigDecimal;

public record TransactionDTO(Long senderId, Long receiverId, BigDecimal value) {
    
}
