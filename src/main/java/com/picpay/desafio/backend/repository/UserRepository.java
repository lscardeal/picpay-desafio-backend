package com.picpay.desafio.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpay.desafio.backend.domain.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findUserById(Long id);

    Optional<User> findUserByDocument(String document);

    Optional<User> findUserByEmail(String email);
}
