package com.devsu.movimientos.domain.dto.requests;

import com.devsu.movimientos.domain.validation.constraints.ExistsAccount;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MovementRequest implements Serializable {
    @ExistsAccount(message = "El nro de cta no existe")
    private String numeroCuenta;
    @NotNull(message = "No puede ser nulo el valor")
    private BigDecimal valor;
}
