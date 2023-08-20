package com.picpay.desafio.backend.domain.entity.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.picpay.desafio.backend.domain.entity.user.UserAccount;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Deposit {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "RECEIVER_ID", nullable = false)
    private UserAccount receiverAccount;

    @Column(name = "DEPOSIT_VALUE", nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public Deposit(UserAccount receiverAccount, BigDecimal value) {
        this.receiverAccount = receiverAccount;
        this.value = value;
        this.timestamp = LocalDateTime.now();
    }
}
