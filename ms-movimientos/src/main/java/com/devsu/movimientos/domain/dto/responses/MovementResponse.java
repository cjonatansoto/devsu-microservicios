package com.devsu.movimientos.domain.dto.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MovementResponse implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private String estado;
    private BigDecimal movimiento;
    private BigDecimal saldoDisponible;
}
