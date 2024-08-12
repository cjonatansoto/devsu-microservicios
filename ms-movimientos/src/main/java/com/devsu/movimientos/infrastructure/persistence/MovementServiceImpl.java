package com.devsu.movimientos.infrastructure.persistence;

import com.devsu.movimientos.domain.dto.requests.MovementRequest;
import com.devsu.movimientos.domain.dto.responses.MovementResponse;
import com.devsu.movimientos.domain.entities.Account;
import com.devsu.movimientos.domain.entities.Movement;
import com.devsu.movimientos.domain.repositories.AccountRepository;
import com.devsu.movimientos.domain.repositories.ClientRepository;
import com.devsu.movimientos.domain.repositories.MovementRepository;
import com.devsu.movimientos.domain.services.MovementService;
import com.devsu.movimientos.exceptions.customs.DataUnavailableException;
import com.devsu.movimientos.exceptions.customs.DateOutOfRangeException;
import com.devsu.movimientos.exceptions.customs.InsufficientBalanceException;
import com.devsu.movimientos.util.mapper.MovementMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovementServiceImpl implements MovementService {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final MovementMapper movementMapper;

    @Override
    public MovementResponse create(MovementRequest movementRequest) {
        // Obtener la cuenta
        var account = getByAccountNumber(movementRequest.getNumeroCuenta());
        // Obtener el valor de la transacción y el saldo actual
        var transactionValue = movementRequest.getValor();
        var currentBalance = account.getBalance();
        // Verificar si la cuenta tiene un saldo suficiente
        if (currentBalance.add(transactionValue).compareTo(BigDecimal.ZERO) < 0) {
            log.info("Cliente sin saldo suficiente para realizar la transacción");
            throw new InsufficientBalanceException("Saldo insuficiente para realizar la transacción.");
        }
        // Calcular el nuevo saldo
        var newBalance = currentBalance.add(transactionValue);
        var initialBalance = currentBalance;
        // Guardar el movimiento en la base de datos
        var movementSave = this.movementRepository.save(Movement.builder()
                .account(account)
                .balanceInitial(initialBalance)
                .amount(transactionValue)
                .balance(newBalance)
                .build());
        // Actualizar el saldo de la cuenta y guardarla
        account.setBalance(newBalance);
        this.accountRepository.save(account);
        return movementMapper.toMovementResponse(movementSave, account, initialBalance, transactionValue);
    }

    @Override
    public List<MovementResponse> reportClientWithDate(LocalDate dateStart, LocalDate dateEnd, Long clientId) {
        validateDateRange(dateStart, dateEnd);
        var reports = movementRepository.findAllByDateBetweenAndAccount_ClientId(dateStart, dateEnd, clientId, Sort.by(Sort.Direction.ASC, "date"));
        if (reports.isEmpty()) {
            throw new DataUnavailableException("No se encontraron registros para el rango de fechas proporcionado.");
        }
        return reports.stream()
                .map(movementMapper::entityToResponse)
                .toList();
    }


    @Override
    public List<MovementResponse> reportAll() {
        List<Movement> reports = Optional.of(movementRepository.findAll())
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new DataUnavailableException("No existen registros"));
        Comparator<Movement> byDateDescending = Comparator.comparing(Movement::getDate).reversed();
        return reports.stream()
                .sorted(byDateDescending)
                .map(movementMapper::entityToResponse)
                .toList();
    }

    private void validateDateRange(LocalDate dateStart, LocalDate dateEnd) {
        Optional.ofNullable(dateStart).orElseThrow(() ->
                new DateOutOfRangeException("La fecha de inicio no puede ser nula"));
        Optional.ofNullable(dateEnd).orElseThrow(() ->
                new DateOutOfRangeException("La fecha de fin no puede ser nula"));
        if (dateStart.isAfter(dateEnd)) {
            throw new DateOutOfRangeException("La fecha de inicio no puede ser posterior a la fecha de fin.");
        }
    }

    private Account getByAccountNumber(String accountNumber) {
        return accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new NoSuchElementException("Account number not found: " + accountNumber));
    }
}
