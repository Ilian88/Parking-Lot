package com.example.parkinglot.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Qualifier;

@Entity
@Table(name = "buses")
@Qualifier("bus")
public class BusEntity extends VehicleEntity {
    private static final int HOURLY_PRICE = 5;
    private static final int WHOLE_DAY_PRICE = 50;

    private ParkingSpace parkingSpace;

    @OneToOne(mappedBy = "bus")
    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public BusEntity setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
        return this;
    }

    public void exitParking() {
        this.calculateTotalPrice(HOURLY_PRICE, WHOLE_DAY_PRICE);
    }
}
