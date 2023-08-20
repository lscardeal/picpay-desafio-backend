package com.picpay.desafio.backend.domain.entity.user;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.picpay.desafio.backend.domain.entity.transaction.Transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USER_ACCOUNT")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserAccount {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private BigDecimal balance;

    @OneToMany
    @JoinColumn(name = "transaction_id", nullable = false)
    private ArrayList<Transaction> transactions;

    public UserAccount(User user, BigDecimal balance) {
        this.user = user;
        this.balance = balance;
        this.transactions = new ArrayList<Transaction>();
    }
}