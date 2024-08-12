package com.devsu.movimientos.domain.dto.requests;


import com.devsu.movimientos.domain.enums.AccountType;
import com.devsu.movimientos.domain.enums.Status;
import com.devsu.movimientos.domain.validation.constraints.EnumExist;
import com.devsu.movimientos.domain.validation.constraints.ExistsClient;
import com.devsu.movimientos.domain.validation.constraints.UniqueNumber;
import jakarta.validation.constraints.Min;
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
public class AccountRequest implements Serializable {
    @UniqueNumber(message = "El numero de cta. ya existe")
    private String numeroCuenta;
    @EnumExist(enumClass = AccountType.class ,message = "El valor ingresado no existe (CORRIENTE, AHORRO)")
    private String tipoCuenta;
    @Min(value = 0, message = "El saldo inicial debe ser mayor a cero")
    private BigDecimal saldoInicial;
    @EnumExist(enumClass = Status.class, message = "El valor ingresado no existe (True, False)")
    private String estado;
    @ExistsClient(message = "El cliente no existe")
    private Long clienteId;
}
