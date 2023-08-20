package com.picpay.desafio.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpay.desafio.backend.domain.entity.transaction.Transference;

public interface TransferenceRepository extends JpaRepository<Transference, Long> {
    

}
