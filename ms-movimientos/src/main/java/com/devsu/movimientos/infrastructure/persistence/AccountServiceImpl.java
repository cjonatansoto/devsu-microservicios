package com.devsu.movimientos.infrastructure.persistence;


import com.devsu.movimientos.domain.dto.requests.AccountRequest;
import com.devsu.movimientos.domain.dto.responses.AccountResponse;
import com.devsu.movimientos.domain.dto.responses.ClientResponse;
import com.devsu.movimientos.domain.entities.Account;
import com.devsu.movimientos.domain.repositories.AccountRepository;
import com.devsu.movimientos.domain.repositories.ClientRepository;
import com.devsu.movimientos.domain.services.AccountService;
import com.devsu.movimientos.exceptions.customs.DataUnavailableException;
import com.devsu.movimientos.util.mapper.AccountMapper;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final ClientRepository clientRepository;

    @Override
    public List<AccountResponse> findAll() {
        log.info("Searching for all accounts");
        var accounts = accountRepository.findAll()
                .stream()
                .map(accountMapper::entityToResponse)
                .toList();
        if (accounts.isEmpty()) {
            log.warn("No accounts found");
            throw new DataUnavailableException("No accounts found");
        }
        return accounts;
    }

    @Override
    @Transactional
    public AccountResponse create(AccountRequest accountRequest) {
        log.info("Creating account with request: {}", accountRequest);
        Account account = accountMapper.requestToEntity(accountRequest);
        ClientResponse client = getClientById(account.getClientId());
        account.setClientId(client.getClienteId());
        account.setClientName(client.getNombre());
        Account savedAccount = accountRepository.save(account);
        log.info("Account successfully created: {}", savedAccount);
        return accountMapper.entityToResponse(savedAccount);
    }

    @Override
    public AccountResponse searchByAccountNumber(String accountNumber) {
        log.info("Searching for account number: {}", accountNumber);
        Account account = getByAccountNumber(accountNumber);
        return accountMapper.entityToResponse(account);
    }

    @Override
    @Transactional
    public void delete(String accountNumber) {
        log.info("Deleting account with number: {}", accountNumber);
        Account accountToDelete = getByAccountNumber(accountNumber);
        accountRepository.delete(accountToDelete);
        log.info("Account with number {} deleted successfully", accountNumber);
    }

    private Account getByAccountNumber(String accountNumber) {
        return accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new DataUnavailableException("No existe la cuenta: " + accountNumber));
    }

    private ClientResponse getClientById(Long clientId) {
        try {
            var client = this.clientRepository.findById(clientId).get();
            return client;
        } catch (FeignException.NotFound e) {
            throw new DataUnavailableException("El cliente no existe");
        }
    }

}
