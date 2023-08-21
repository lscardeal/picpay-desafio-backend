package com.picpay.desafio.backend.domain.entity.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.picpay.desafio.backend.domain.entity.user.UserAccount;

import jakarta.persistence.Column;
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
@Table(name = "TRANSFERENCE")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transference {
    
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SENDER_ID", nullable = false)
    @JsonBackReference
    private UserAccount senderAccount;

    @ManyToOne
    @JoinColumn(name = "RECEIVER_ID", nullable = false)
    @JsonBackReference
    private UserAccount receiverAccount;

    @Column(name = "TRANSFERENCE_VALUE", nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public Transference(UserAccount senderAccount, UserAccount receiverAccount, BigDecimal value) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.value = value;
        this.timestamp = LocalDateTime.now();
    }
}
