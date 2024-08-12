package com.devsu.movimientos.util.mapper;

import com.devsu.movimientos.domain.dto.responses.MovementResponse;
import com.devsu.movimientos.domain.entities.Account;
import com.devsu.movimientos.domain.entities.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper
public interface MovementMapper {

    @Mapping(target = "fecha", source = "movement.date")
    @Mapping(target = "cliente", source = "account.clientName")
    @Mapping(target = "numeroCuenta", source = "account.number")
    @Mapping(target = "tipo", source = "account.accountType.name")
    @Mapping(target = "saldoInicial", source = "initialBalance")
    @Mapping(target = "estado", source = "account.status.name")
    @Mapping(target = "movimiento", source = "transactionValue")
    @Mapping(target = "saldoDisponible", source = "movement.balance")
    MovementResponse toMovementResponse(Movement movement, Account account, BigDecimal initialBalance, BigDecimal transactionValue);


    @Mapping(source = "date", target = "fecha")
    @Mapping(source = "account.clientName", target = "cliente")
    @Mapping(source = "account.number", target = "numeroCuenta")
    @Mapping(source = "account.accountType", target = "tipo")
    @Mapping(source = "balanceInitial", target = "saldoInicial")
    @Mapping(source = "account.status", target = "estado")
    @Mapping(source = "amount", target = "movimiento")
    @Mapping(source = "balance", target = "saldoDisponible")
    MovementResponse entityToResponse(Movement movement);
}
