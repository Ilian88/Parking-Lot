package com.example.parkinglot.model.entity;

import com.example.parkinglot.model.enums.ParkingSpaceStatus;
import jakarta.persistence.*;


@Entity
@Table(name = "parking_space")
public class ParkingSpace extends BaseEntity {
    private CarEntity car;
    private BusEntity bus;

    private ParkingSpaceStatus status;

    public ParkingSpace() {
    }

    @OneToOne
    public CarEntity getCar() {
        return car;
    }

    public ParkingSpace setCar(CarEntity car) {
        this.car = car;
        return this;
    }

    @OneToOne
    public BusEntity getBus() {
        return bus;
    }

    public ParkingSpace setBus(BusEntity bus) {
        this.bus = bus;
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
