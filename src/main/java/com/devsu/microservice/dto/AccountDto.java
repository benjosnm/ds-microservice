package com.devsu.microservice.dto;

import com.devsu.microservice.entities.AccountEntity;

import java.math.BigDecimal;
import java.util.Objects;

public class AccountDto {
    private Long id;
    private Long clientId;
    private AccountType type;
    private BaseStatus status;
    private BigDecimal openingBalance;

    public AccountDto() {
    }

    public AccountDto(Long id, Long clientId, AccountType type, BaseStatus status, BigDecimal openingBalance) {
        this.id = id;
        this.clientId = clientId;
        this.type = type;
        this.status = status;
        this.openingBalance = openingBalance;
    }

    public AccountDto(AccountEntity accountEntity) {
        this.id = accountEntity.getId();
        this.type = AccountType.valueOf(accountEntity.getAccountType());
        this.openingBalance = accountEntity.getOpeningBalance();
        this.status = BaseStatus.valueOf(accountEntity.getStatus());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public BaseStatus getStatus() {
        return status;
    }

    public void setStatus(BaseStatus status) {
        this.status = status;
    }

    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(BigDecimal openingBalance) {
        this.openingBalance = openingBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDto that = (AccountDto) o;
        return Objects.equals(id, that.id) && Objects.equals(clientId, that.clientId) && type == that.type && status == that.status && Objects.equals(openingBalance, that.openingBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, type, status, openingBalance);
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", type=" + type +
                ", status=" + status +
                ", openingBalance=" + openingBalance +
                '}';
    }
}
