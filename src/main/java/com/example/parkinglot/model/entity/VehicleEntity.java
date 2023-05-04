package com.example.parkinglot.model.entity;

import com.example.parkinglot.model.enums.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
public class VehicleEntity extends BaseEntity {

    private String licensePlate;

    private VehicleType vehicleType;

    private LocalDateTime enteredAt;

    private LocalDateTime leftAt;
    
    private ParkingSpace parkingSpace;

    private Sale sale;


    public VehicleEntity() {
    }

    @Column(name = "license_plate", nullable = false, unique = true)
    @Size(min = 4, message = "License plate bust be at least 4 symbols")
    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleEntity setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    @Column(name = "entered_at", nullable = false)
    public LocalDateTime getEnteredAt() {
        return enteredAt;
    }

    public VehicleEntity setEnteredAt(LocalDateTime enteredAt) {
        this.enteredAt = enteredAt;
        return this;
    }

    @Column(name = "left_at")
    public LocalDateTime getLeftAt() {
        return leftAt;
    }

    public VehicleEntity setLeftAt(LocalDateTime leftAt) {
        this.leftAt = leftAt;
        return this;
    }

    @OneToOne(mappedBy = "vehicle")
    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    @Enumerated(EnumType.STRING)
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public VehicleEntity setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public VehicleEntity setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
        return this;
    }

    @OneToOne(mappedBy = "vehicle")
    public Sale getSale() {
        return sale;
    }

    public VehicleEntity setSale(Sale sale) {
        this.sale = sale;
        return this;
    }
}
