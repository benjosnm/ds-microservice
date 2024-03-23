package com.devsu.microservice.repositories;

import com.devsu.microservice.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    Optional<TransactionEntity> findTopByAccountIdOrderByIdDesc(Long accountId);
    List<TransactionEntity> findByAccountId(Long accountId);
}
