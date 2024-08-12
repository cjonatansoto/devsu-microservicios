package com.devsu.movimientos.domain.validation.constraints;

import com.devsu.movimientos.domain.validation.ExistsAccountValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = ExistsAccountValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "Value cannot be null")
public @interface ExistsAccount {
    String message() default "value not found by id";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}