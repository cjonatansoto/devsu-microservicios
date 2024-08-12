package com.devsu.movimientos.infrastructure.web;

import com.devsu.movimientos.domain.dto.requests.MovementRequest;
import com.devsu.movimientos.domain.dto.responses.MovementResponse;
import com.devsu.movimientos.domain.services.MovementService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "movimientos")
@AllArgsConstructor
public class MovementController {

    private final MovementService movementService;

    @PostMapping
    public ResponseEntity<MovementResponse> create(@Valid @RequestBody MovementRequest request) {
        return ResponseEntity.ok(movementService.create(request));
    }


    @GetMapping(value = "/reportes/{clienteId}")
    public ResponseEntity<List<MovementResponse>> reportClientWithDate(
            @PathVariable Long clienteId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaInicial,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaFin) {
        return ResponseEntity.ok(this.movementService.reportClientWithDate(fechaInicial, fechaFin, clienteId));
    }

    @GetMapping(value = "/reportes")
    public ResponseEntity<List<MovementResponse>> reportAll() {
        return ResponseEntity.ok(this.movementService.reportAll());
    }
}
