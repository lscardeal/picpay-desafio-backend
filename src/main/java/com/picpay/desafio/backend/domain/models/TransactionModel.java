package com.picpay.desafio.backend.domain.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonSerialize
@JsonDeserialize
public class TransactionModel {
    
    private String sender;

    private String receiver;

    private BigDecimal value;
}
