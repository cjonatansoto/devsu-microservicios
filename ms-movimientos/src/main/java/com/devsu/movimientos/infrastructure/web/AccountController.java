package com.devsu.movimientos.infrastructure.web;


import com.devsu.movimientos.domain.dto.requests.AccountRequest;
import com.devsu.movimientos.domain.dto.responses.AccountResponse;
import com.devsu.movimientos.domain.services.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "cuentas")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountResponse>> findAll() {
        var response = this.accountService.findAll();
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody AccountRequest request) {
        return ResponseEntity.ok(accountService.create(request));
    }

    @GetMapping(value = "/{accountNumber}")
    public ResponseEntity<AccountResponse> findById(@PathVariable String accountNumber) {
        return ResponseEntity.ok(this.accountService.searchByAccountNumber(accountNumber));
    }


    @DeleteMapping(value = "/{accountNumber}")
    public void delete(@PathVariable String accountNumber) {
        this.accountService.delete(accountNumber);
    }

}
