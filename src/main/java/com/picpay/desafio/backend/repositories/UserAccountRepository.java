package com.picpay.desafio.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpay.desafio.backend.domain.entity.user.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

}
