package com.devsu.microservice.services;

import com.devsu.microservice.repositories.AccountRepository;
import com.devsu.microservice.repositories.ClientRepository;
import com.devsu.microservice.dto.AccountDto;
import com.devsu.microservice.entities.AccountEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public AccountService(AccountRepository accountRepository, ClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    public List<AccountDto> getAllAccounts() {
        List<AccountEntity> result = accountRepository.findAll();

        return result.stream().map(AccountDto::new).toList();
    }

    public Optional<AccountDto> getAccountById(Long id) {
        return accountRepository.findById(id).map(AccountDto::new);
    }

    public AccountDto createAccount(AccountDto accountDto) {
        AccountEntity accountEntity = new AccountEntity(accountDto);
        clientRepository.findById(accountDto.getClientId()).ifPresent(accountEntity::setClient);
        return new AccountDto(accountRepository.save(accountEntity));
    }

    public Optional<AccountDto> updateAccount(Long id, AccountDto accountDto) {
        Optional<AccountEntity> originalAccount = accountRepository.findById(id);

        if(originalAccount.isPresent()) {
            AccountEntity accountEntity = new AccountEntity(accountDto);
            clientRepository.findById(accountDto.getClientId()).ifPresent(accountEntity::setClient);
            return Optional.of(new AccountDto(accountRepository.save(accountEntity)));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteAccount(Long id) {
        Optional<AccountEntity> originalAccount = accountRepository.findById(id);

        if(originalAccount.isPresent()) {
            accountRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<AccountDto> patchAccount(Long id, AccountDto accountDto) {
        Optional<AccountEntity> originalAccount = accountRepository.findById(id);

        if(originalAccount.isPresent()) {
            AccountEntity accountEntity = originalAccount.get();
            if(accountDto.getType() != null) {
                accountEntity.setAccountType(accountDto.getType().name());
            }
            if(accountDto.getStatus() != null) {
                accountEntity.setStatus(accountDto.getStatus().name());
            }
            if(accountDto.getClientId() != null) {
                clientRepository.findById(accountDto.getClientId()).ifPresent(accountEntity::setClient);
            }
            return Optional.of(new AccountDto(accountRepository.save(accountEntity)));
        } else {
            return Optional.empty();
        }
    }

    public List<AccountDto> getAccountsByClientId(Long clientId) {
        List<AccountEntity> result = accountRepository.findAllByClientId(clientId);

        return result.stream().map(AccountDto::new).toList();
    }
}
