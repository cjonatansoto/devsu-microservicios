package com.devsu.clientes.infrastructure.web;


import com.devsu.clientes.domain.dto.requests.ClientRequest;
import com.devsu.clientes.domain.dto.responses.ClientResponse;
import com.devsu.clientes.domain.services.ClientService;
import com.devsu.clientes.exceptions.responses.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "clientes")
@Tag(name = "Clientes")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @Operation(description = "Obtención del listado de clientes")
    @ApiResponse(responseCode = "200", description = "Solicitud exitosa. Se devuelve un listado de clientes.")
    @ApiResponse(responseCode = "404", description = "No se encontraron clientes. El listado está vacío.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor. No se pudo procesar la solicitud.")
    @GetMapping
    public ResponseEntity<List<ClientResponse>> findAll() {
        return ResponseEntity.ok(this.clientService.findAll());
    }

    @Operation(description = "Obtiene la información de un cliente específico utilizando su identificador único (ID).")
    @ApiResponse(responseCode = "200", description = "La información del cliente ha sido recuperada exitosamente.")
    @ApiResponse(
            responseCode = "404",
            description = "No se encontró ningún cliente correspondiente al ID proporcionado.",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }
    )
    @ApiResponse(responseCode = "500", description = "Se produjo un error interno al procesar la solicitud. Intente nuevamente más tarde.")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.clientService.findById(id));
    }

    @Operation(description = "Crear un nuevo cliente")
    @ApiResponse(responseCode = "200", description = "Cliente creado exitosamente")
    @ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el servidor al intentar crear el cliente")
    @PostMapping
    public ResponseEntity<ClientResponse> create(@Valid @RequestBody ClientRequest request) {
        return ResponseEntity.ok(this.clientService.create(request));
    }

    @Operation(description = "Actualiza la información del cliente especificado")
    @ApiResponse(responseCode = "200", description = "La información del cliente se ha actualizado correctamente.")
    @ApiResponse(responseCode = "404", description = "No se pudo encontrar el cliente especificado para la actualización.")
    @ApiResponse(responseCode = "500", description = "Se ha producido un error interno al procesar la solicitud de actualización.")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientResponse> update(@Valid @PathVariable Long id, @RequestBody ClientRequest clientRequest) {
        return ResponseEntity.ok(this.clientService.update(id, clientRequest));
    }


    @Operation(description = "Elimina un cliente de forma suave, permitiendo la recuperación de datos si es necesario.")
    @ApiResponse(responseCode = "200", description = "Eliminación del cliente realizada con éxito.")
    @ApiResponse(responseCode = "404", description = "No se encontró un cliente con el identificador proporcionado.")
    @ApiResponse(responseCode = "500", description = "Se produjo un error interno al intentar eliminar el cliente.")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        this.clientService.delete(id);
    }


}
