package com.devsu.movimientos.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    True("True"),
    False("False");
    private final String name;
    public boolean toBooleanValue() {
        return True.name.equals(this.getName());
    }

}
