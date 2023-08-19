package com.picpay.desafio.backend.domain.entity.user;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.picpay.desafio.backend.domain.entity.transaction.Transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER_ACCOUNT")
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserAccount {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private BigDecimal balance;

    @OneToMany
    @JoinColumn(name = "transaction_id")
    private ArrayList<Transaction> transactions;
}