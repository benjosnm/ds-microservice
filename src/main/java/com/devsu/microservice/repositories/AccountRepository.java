package com.devsu.microservice.repositories;

import com.devsu.microservice.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findAllByClientId(Long clientId);
}
