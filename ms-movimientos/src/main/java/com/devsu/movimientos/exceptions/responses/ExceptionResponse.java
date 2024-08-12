package com.devsu.movimientos.exceptions.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse implements Serializable {
    private String status;
    private Integer code;
}
