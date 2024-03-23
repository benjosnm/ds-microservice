package com.devsu.microservice.services;

import com.devsu.microservice.dto.TransactionDto;
import com.devsu.microservice.entities.TransactionEntity;
import com.devsu.microservice.repositories.AccountRepository;
import com.devsu.microservice.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public List<TransactionDto> getAllTransactions() {
        List<TransactionEntity> result = transactionRepository.findAll();

        return result.stream().map(TransactionDto::new).toList();
    }

    public Optional<TransactionDto> getTransactionById(Long id) {
        return transactionRepository.findById(id).map(TransactionDto::new);
    }

    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Optional<TransactionEntity> lastTransaction =
                transactionRepository.findTopByAccountIdOrderByIdDesc(transactionDto.getAccountId());

        if(lastTransaction.isPresent()) {
            transactionDto.setBalance(lastTransaction.get().getBalance().add(transactionDto.getAmount()));
        } else {
            accountRepository.findById(transactionDto.getAccountId()).ifPresent(accountEntity -> {
                transactionDto.setBalance(accountEntity.getOpeningBalance().add(transactionDto.getAmount()));
            });
        }

        // TODO validate if the account has enough balance to make the transaction
        // TODO validate if the account is active
        // TODO validate if the client is active

        TransactionEntity transactionEntity = new TransactionEntity(transactionDto);
        return new TransactionDto(transactionRepository.save(transactionEntity));
    }

    public List<TransactionDto> getTransactionsByAccount(Long accountId) {
        List<TransactionEntity> result = transactionRepository.findByAccountId(accountId);

        return result.stream().map(TransactionDto::new).toList();
    }
}
