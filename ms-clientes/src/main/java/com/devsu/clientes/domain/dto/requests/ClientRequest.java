package com.devsu.clientes.domain.dto.requests;

import com.devsu.clientes.domain.validation.constraints.EnumExist;
import com.devsu.clientes.domain.enums.Gender;
import com.devsu.clientes.domain.enums.Status;
import com.devsu.clientes.util.constants.CoreConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClientRequest implements Serializable {

    @Schema(example = "Juan Perez", required = true)
    @NotEmpty(message = CoreConstants.ERROR_VALIDATION_MANDATORY)
    private String nombre;
    @Schema(type = "string", allowableValues = {"Masculino","Femenino","Indefindio"})
    @NotNull(message = CoreConstants.ERROR_VALIDATION_MANDATORY)
    @EnumExist(enumClass = Gender.class, message = "El valor ingresado no existe (MASCULINO, FEMENINO, INDEFINIDO)")
    private String genero;
    @Schema(example = "20", required = true)
    @NotNull(message = CoreConstants.ERROR_VALIDATION_MANDATORY)
    @Positive(message = "El valor debe ser mayor a cero")
    private Integer edad;
    @Schema(example = "192079082", required = true)
    @NotEmpty(message = CoreConstants.ERROR_VALIDATION_MANDATORY)
    private String identificacion;
    @Schema(example = "Avenida Providencia 1234, Departamento 56, Providencia, Santiago, Chile", required = true)
    @NotEmpty(message = CoreConstants.ERROR_VALIDATION_MANDATORY)
    private String direccion;
    @Schema(example = "+56947949317", required = true)
    @NotEmpty(message = CoreConstants.ERROR_VALIDATION_MANDATORY)
    private String telefono;
    @Schema(example = "juan1234", required = true)
    @NotEmpty(message = CoreConstants.ERROR_VALIDATION_MANDATORY)
    private String contrasenia;
    @Schema(example = "true", required = true)
    @NotNull(message = CoreConstants.ERROR_VALIDATION_MANDATORY)
    @EnumExist(enumClass = Status.class, message = "El valor ingresado no existe (True, False)")
    private String estado;
}
