package com.example.parkinglot.model.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class EnterVehicleDTO {
    private String licensePlate;

    private LocalDateTime enteredAt;

    private String vehicleType;

    public EnterVehicleDTO() {
    }

    @Size(min = 4, message = "Plate number must be at least 4 symbols")
    public String getlicensePlate() {
        return licensePlate;
    }

    public EnterVehicleDTO setlicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    @FutureOrPresent(message = "Date must not be in the past")
    public LocalDateTime getEnteredAt() {
        return enteredAt;
    }

    public EnterVehicleDTO setEnteredAt(LocalDateTime enteredAt) {
        this.enteredAt = enteredAt;
        return this;
    }

    @NotBlank(message = "Vehicle type must be either CAR or BUS")
    public String getVehicleType() {
        return vehicleType;
    }

    public EnterVehicleDTO setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }
}
