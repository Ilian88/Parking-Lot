package com.example.parkinglot.exceptions;

public class InvalidVehicleTypeException extends RuntimeException{
    public InvalidVehicleTypeException(String exception) {
        super(exception);
    }
    public InvalidVehicleTypeException(String exception, Throwable err) {
        super(exception, err);
    }
}
