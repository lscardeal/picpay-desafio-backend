package com.picpay.desafio.backend.domain.entity.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.picpay.desafio.backend.domain.entity.transaction.Deposit;
import com.picpay.desafio.backend.domain.entity.transaction.Transference;

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
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private User user;

    @Column(nullable = false)
    private BigDecimal balance;

    @OneToMany(mappedBy="senderAccount")
    private List<Transference> transferencesSended;

    @OneToMany(mappedBy="receiverAccount")
    private List<Transference> transferencesReceived;

    @OneToMany(mappedBy="receiverAccount")
    private List<Deposit> depositsReceived;

    public UserAccount(User user, BigDecimal balance) {
        this.user = user;
        this.balance = balance;
        this.transferencesSended = new ArrayList<Transference>();
        this.transferencesReceived = new ArrayList<Transference>();
        this.depositsReceived = new ArrayList<Deposit>();
    }
}