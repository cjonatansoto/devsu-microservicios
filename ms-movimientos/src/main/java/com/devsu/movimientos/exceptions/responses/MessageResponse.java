package com.devsu.movimientos.exceptions.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse extends ExceptionResponse{
    private String error;
    private HashMap<String, String> errors;
    public MessageResponse(String status, Integer code, String error) {
        super(status, code);
        this.error = error;
        this.errors = new HashMap<>();
    }
    public MessageResponse(String status, Integer code, HashMap<String, String> errors) {
        super(status, code);
        this.errors = errors;
    }
}
