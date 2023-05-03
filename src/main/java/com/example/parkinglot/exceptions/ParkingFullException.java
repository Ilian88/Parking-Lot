package com.example.parkinglot.exceptions;

public class ParkingFullException extends RuntimeException{
    public ParkingFullException(String exception) {
        super(exception);
    }
}
