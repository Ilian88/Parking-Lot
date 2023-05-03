package com.example.parkinglot.errorHandling;

import com.example.parkinglot.exceptions.ElementAlreadyExistsException;
import com.example.parkinglot.exceptions.InvalidVehicleTypeException;
import com.example.parkinglot.exceptions.ParkingFullException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { ElementAlreadyExistsException.class })
    public ResponseEntity<ApiError> elementAlreadyExists(ElementAlreadyExistsException ex) {
        return ResponseEntity.badRequest().body(
                new ApiError(ex.getMessage(), "err0022" )
        );
    }

    @ExceptionHandler(value = { ParkingFullException.class })
    public ResponseEntity<ApiError> parkingFull(ParkingFullException ex) {
        return ResponseEntity.badRequest().body(
                new ApiError(ex.getMessage(), "f0001")
        );
    }

    @ExceptionHandler(value = { InvalidVehicleTypeException.class })
    public ResponseEntity<ApiError> invalidVehicleType(InvalidVehicleTypeException ex) {
        return ResponseEntity.badRequest().body(
                new ApiError(ex.getMessage(), "err002")
        );
    }

    @ExceptionHandler(value = { NoSuchElementException.class })
    public ResponseEntity<Void> elementNotFound(NoSuchElementException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ApiError> defaultError(Exception ex) {
        return ResponseEntity.internalServerError().body(
                new ApiError("There was problem processing your request", "err0001")
        );
    }
}
