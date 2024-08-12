package com.devsu.clientes.domain.validation;

import com.devsu.clientes.domain.validation.constraints.EnumExist;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class EnumExistValidator implements ConstraintValidator<EnumExist, String> {
    private List<String> values = new ArrayList<String>();

    @Override
    public void initialize(EnumExist enumValidator) {
        values = new ArrayList<String>();
        Enum<?>[] enumConstants = enumValidator.enumClass().getEnumConstants();
        for (Enum<?> enumConstant : enumConstants) {
            values.add(enumConstant.toString());
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return values.contains(value);
    }
}