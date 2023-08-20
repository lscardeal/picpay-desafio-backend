package com.picpay.desafio.backend.domain.dtos.transaction;

import java.math.BigDecimal;

public record DepositDTO(Long userAccountId, BigDecimal value) {
    
}
