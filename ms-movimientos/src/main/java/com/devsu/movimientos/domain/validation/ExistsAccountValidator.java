package com.devsu.movimientos.domain.validation;

import com.devsu.movimientos.domain.repositories.AccountRepository;
import com.devsu.movimientos.domain.validation.constraints.ExistsAccount;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class ExistsAccountValidator implements ConstraintValidator<ExistsAccount, String> {
    private final AccountRepository accountRepository;
    @Override
    public boolean isValid(String field, ConstraintValidatorContext context) {
        return accountRepository.findByNumber(field).isPresent();
    }
}
