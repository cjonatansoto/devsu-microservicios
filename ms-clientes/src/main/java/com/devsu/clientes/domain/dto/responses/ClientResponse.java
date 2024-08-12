package com.devsu.clientes.domain.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClientResponse implements Serializable {
    private Long clienteId;
    private String nombre;
    private String direccion;
    private String telefono;
    private String contrasenia;
    private String estado;
    private String genero;
    private Integer edad;
    private String identificacion;
}
