package com.parking.pos.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VehicleAlreadyCheckedInException.class)
    public ErrorResponse handleVehicleAlreadyCheckedIn(VehicleAlreadyCheckedInException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(VehicleNotFoundException.class)
    public ErrorResponse handleVehicleNotFound(VehicleNotFoundException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Invalid input");

        return new ErrorResponse(errorMessage);
    }

    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handleGeneric(RuntimeException ex) {
        ex.printStackTrace();
        return new ErrorResponse("Terjadi kesalahan pada server.");

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleAll(Exception ex) {
        ex.printStackTrace();
        return new ErrorResponse("Terjadi kesalahan pada server.");
    }
}

