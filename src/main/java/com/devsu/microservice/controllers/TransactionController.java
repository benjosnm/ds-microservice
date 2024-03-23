package com.devsu.microservice.controllers;

import com.devsu.microservice.dto.ClientDto;
import com.devsu.microservice.dto.TransactionDto;
import com.devsu.microservice.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionDto> getTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("{id}")
    public ResponseEntity<TransactionDto> getTransactionById(Long id) {
        return ResponseEntity.of(transactionService.getTransactionById(id));
    }

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(TransactionDto transaction) {
        TransactionDto newTransaction = transactionService.createTransaction(transaction);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(newTransaction.getId())
                .toUri();
        return ResponseEntity.created(location).body(newTransaction);
    }
}
