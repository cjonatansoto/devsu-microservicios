package com.devsu.movimientos.domain.services;


import com.devsu.movimientos.domain.dto.requests.MovementRequest;
import com.devsu.movimientos.domain.dto.responses.MovementResponse;

import java.time.LocalDate;
import java.util.List;

public interface MovementService {
    MovementResponse create(MovementRequest movementRequest);
    List<MovementResponse> reportClientWithDate(LocalDate dateStart, LocalDate dateEnd, Long clientId);
    List<MovementResponse> reportAll();
}
