package com.devsu.clientes.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    Masculino("Masculino"),
    Femenino("Femenino"),
    Indefinido("Indefinido");
    private final String name;
}



