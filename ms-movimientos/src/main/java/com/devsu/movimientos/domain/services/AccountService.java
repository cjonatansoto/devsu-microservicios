package com.devsu.movimientos.domain.services;

import com.devsu.movimientos.domain.dto.requests.AccountRequest;
import com.devsu.movimientos.domain.dto.responses.AccountResponse;

import java.util.List;

public interface AccountService {
    List<AccountResponse> findAll();
    AccountResponse create(AccountRequest accountRequest);
    AccountResponse searchByAccountNumber(String accountNumber);
    void delete(String accountNumber);
}
