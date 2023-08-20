package com.picpay.desafio.backend.domain.dtos.transaction;

import java.math.BigDecimal;

public record TransferenceDTO(Long senderId, Long receiverId, BigDecimal value) {
    
}
