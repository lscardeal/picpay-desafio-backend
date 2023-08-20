package com.picpay.desafio.backend.domain.entity.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.picpay.desafio.backend.domain.entity.user.UserAccount;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TRANSACTIONS")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {
    
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserAccount senderAccount;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserAccount receiverAccount;

    private BigDecimal value;

    private LocalDateTime timestamp;

    public Transaction(UserAccount senderAccount, UserAccount receiverAccount, BigDecimal value) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.value = value;
        this.timestamp = LocalDateTime.now();
    }
}
