package com.devsu.movimientos.domain.validation;


import com.devsu.movimientos.domain.repositories.ClientRepository;
import com.devsu.movimientos.domain.validation.constraints.ExistsClient;
import feign.FeignException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class ExistsClientValidator implements ConstraintValidator<ExistsClient, Long> {
    private final ClientRepository clientRepository;
    @Override
    public boolean isValid(Long field, ConstraintValidatorContext context) {
        try {
            var client = this.clientRepository.findById(field).isPresent();
            log.warn("Se encontró un cliente: {}", client);
            return true;
        } catch (FeignException.NotFound e) {
            log.warn("No se encontró un cliente con ID: {}", field);
            return false;
        }
    }
}