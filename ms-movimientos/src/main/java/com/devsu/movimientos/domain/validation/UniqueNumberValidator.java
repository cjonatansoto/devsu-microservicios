package com.devsu.movimientos.domain.validation;

import com.devsu.movimientos.domain.repositories.AccountRepository;
import com.devsu.movimientos.domain.validation.constraints.UniqueNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UniqueNumberValidator implements ConstraintValidator<UniqueNumber, String> {

    private final AccountRepository accountRepository;

    @Override
    public boolean isValid(String field, ConstraintValidatorContext context) {
        return !accountRepository.findByNumber(field).isPresent();
    }
}