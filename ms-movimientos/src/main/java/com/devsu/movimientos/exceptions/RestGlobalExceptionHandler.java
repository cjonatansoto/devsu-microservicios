package com.devsu.movimientos.exceptions;


import com.devsu.movimientos.exceptions.customs.DataUnavailableException;
import com.devsu.movimientos.exceptions.customs.DateOutOfRangeException;
import com.devsu.movimientos.exceptions.customs.InsufficientBalanceException;
import com.devsu.movimientos.exceptions.responses.MessageResponse;
import com.devsu.movimientos.util.constants.CoreConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class RestGlobalExceptionHandler {

    /**
     * Maneja excepciones de tipo DataUnavailableException.
     * Devuelve un mensaje de error con un estado 404 (No encontrado).
     *
     * @param ex la excepción de tipo DataUnavailableException
     * @return un objeto MessageResponse con detalles del error
     */
    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponse handleInsufficientBalanceException(InsufficientBalanceException ex) {
        return MessageResponse.builder()
                .error(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(DataUnavailableException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageResponse handleDataUnavailableException(DataUnavailableException ex) {
        return MessageResponse.builder()
                .error(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.name())
                .code(HttpStatus.NOT_FOUND.value())
                .build();
    }

    @ExceptionHandler(DateOutOfRangeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponse handleDateOutOfRangeException(DateOutOfRangeException ex) {
        return MessageResponse.builder()
                .error(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    /**
     * Maneja excepciones de validación de argumentos de método (MethodArgumentNotValidException).
     * Devuelve un mensaje de error con un estado 400 (Solicitud incorrecta).
     *
     * @param ex la excepción de tipo MethodArgumentNotValidException
     * @return un objeto MessageResponse con detalles de las validaciones fallidas
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.warn("MethodArgumentNotValidException: {}", ex.getMessage());
        HashMap<String, String> errors = (HashMap<String, String>) ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField(),
                        fieldError -> fieldError.getDefaultMessage()
                ));
        return MessageResponse.builder()
                .errors(errors)
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    /**
     * Maneja cualquier otra excepción no controlada.
     * Devuelve un mensaje de error con un estado 500 (Error interno del servidor).
     *
     * @param ex la excepción genérica
     * @return un objeto MessageResponse con un mensaje de error estándar
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public MessageResponse handleGeneralException(Exception ex) {
        log.error("Exception caught: {}", ex.getMessage(), ex);
        return MessageResponse.builder()
                .error(CoreConstants.ERROR_INTERNAL_SERVER_ERROR)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
    }
}
