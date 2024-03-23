package com.devsu.microservice.dto;

import com.devsu.microservice.entities.TransactionEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class TransactionDto {
    private Long id;
    private Date date;
    private TransactionType type;
    private BigDecimal amount;
    private BigDecimal balance;
    private Long  accountId;

    public TransactionDto() {
    }

    public TransactionDto(Long id, Date date, TransactionType type, BigDecimal amount, BigDecimal balance, Long accountId) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.accountId = accountId;
    }

    public TransactionDto(TransactionEntity transactionEntity) {
        this.id = transactionEntity.getId();
        this.date = transactionEntity.getDate();
        this.type = TransactionType.valueOf(transactionEntity.getType());
        this.amount = transactionEntity.getAmount();
        this.balance = transactionEntity.getBalance();
        this.accountId = transactionEntity.getAccount().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDto that = (TransactionDto) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && type == that.type && Objects.equals(amount, that.amount) && Objects.equals(balance, that.balance) && Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, type, amount, balance, accountId);
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "id=" + id +
                ", date=" + date +
                ", type=" + type +
                ", amount=" + amount +
                ", balance=" + balance +
                ", accountId=" + accountId +
                '}';
    }
}
