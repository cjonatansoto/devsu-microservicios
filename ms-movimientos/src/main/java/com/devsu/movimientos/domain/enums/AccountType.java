package com.devsu.movimientos.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType {
    CORRIENTE("Corriente"),
    AHORRO("Ahorro");
    private final String name;
}



