package com.devsu.microservice.entities;

import com.devsu.microservice.dto.TransactionDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreatedDate
    private Date date;
    private String type;
    private BigDecimal amount;
    private BigDecimal balance;
    @ManyToOne(targetEntity = AccountEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    public TransactionEntity() {
    }

    public TransactionEntity(Long id, Date date, String type, BigDecimal amount, BigDecimal balance,
                             AccountEntity accountEntity) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.account = accountEntity;
    }

    public TransactionEntity(TransactionDto transactionDto) {
        this.id = transactionDto.getId();
        this.date = transactionDto.getDate();
        this.type = transactionDto.getType().name();
        this.amount = transactionDto.getAmount();
        this.balance = transactionDto.getBalance();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity accountEntity) {
        this.account = accountEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEntity that = (TransactionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(type, that.type) && Objects.equals(amount, that.amount) && Objects.equals(balance, that.balance) && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, type, amount, balance, account);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", balance=" + balance +
                ", account=" + account +
                '}';
    }
}
