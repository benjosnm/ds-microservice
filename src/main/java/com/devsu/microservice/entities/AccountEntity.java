package com.devsu.microservice.entities;

import com.devsu.microservice.dto.AccountDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;
    @Column(name = "type")
    private String accountType;
    @PositiveOrZero
    private BigDecimal openingBalance;
    private String status;

    @ManyToOne(targetEntity = ClientEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    public AccountEntity() {
    }

    public AccountEntity(Long id, String accountType, BigDecimal openingBalance, String status, ClientEntity clientEntity) {
        this.id = id;
        this.accountType = accountType;
        this.openingBalance = openingBalance;
        this.status = status;
        this.client = clientEntity;
    }

    public AccountEntity(AccountDto accountDto) {
        this.id = accountDto.getId();
        this.accountType = accountDto.getType().name();
        this.openingBalance = accountDto.getOpeningBalance();
        this.status = accountDto.getStatus().name();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(BigDecimal openingBalance) {
        this.openingBalance = openingBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity clientEntity) {
        this.client = clientEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity accountEntity = (AccountEntity) o;
        return Objects.equals(id, accountEntity.id) && Objects.equals(accountType, accountEntity.accountType) && Objects.equals(openingBalance, accountEntity.openingBalance) && Objects.equals(status, accountEntity.status) && Objects.equals(client, accountEntity.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountType, openingBalance, status, client);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountType='" + accountType + '\'' +
                ", openingBalance=" + openingBalance +
                ", status='" + status + '\'' +
                ", client=" + client +
                '}';
    }
}
