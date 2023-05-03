package com.example.parkinglot.model.entity;

import com.example.parkinglot.model.enums.ParkingSpaceStatus;
import jakarta.persistence.*;


@Entity
@Table(name = "parking_space")
public class ParkingSpace extends BaseEntity {
    private VehicleEntity vehicle;

    private ParkingSpaceStatus status;

    public ParkingSpace() {
    }

    @OneToOne
    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public ParkingSpace setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public ParkingSpaceStatus getStatus() {
        return status;
    }

    public ParkingSpace setStatus(ParkingSpaceStatus status) {
        this.status = status;
        return this;
    }
}
