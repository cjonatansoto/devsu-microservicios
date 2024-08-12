package com.devsu.movimientos.util.mapper;


import com.devsu.movimientos.domain.dto.requests.AccountRequest;
import com.devsu.movimientos.domain.dto.responses.AccountResponse;
import com.devsu.movimientos.domain.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "numeroCuenta", target = "number")
    @Mapping(source = "tipoCuenta", target = "accountType") // Asumiendo que necesitas convertir a un AccountType
    @Mapping(source = "saldoInicial", target = "balance")
    @Mapping(source = "estado", target = "status") // Asumiendo que necesitas convertir a un Status
    @Mapping(source = "clienteId", target = "clientId")
    Account requestToEntity(AccountRequest accountRequest);

    @Mapping(source = "id", target = "cuentaId")
    @Mapping(source = "number", target = "numeroCuenta")
    @Mapping(source = "accountType", target = "tipoCuenta")
    @Mapping(source = "balance", target = "saldoInicial")
    @Mapping(source = "status", target = "estado")
    @Mapping(source = "clientName", target = "cliente")
    AccountResponse entityToResponse(Account account);


}
