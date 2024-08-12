package com.devsu.movimientos.domain.validation.constraints;

import com.devsu.movimientos.domain.validation.UniqueNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = UniqueNumberValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "Value cannot be null")
public @interface UniqueNumber {
    String message() default "the field must be unique";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
