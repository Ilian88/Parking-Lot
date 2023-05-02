package com.example.parkinglot.model.entity;

import com.example.parkinglot.service.ParkingSpaceService;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Qualifier;

@Entity
@Table(name = "cars")
@Qualifier("car")
public class CarEntity extends VehicleEntity {
    private static final int HOURLY_PRICE = 1;
    private static final int WHOLE_DAY_PRICE = 10;

    private ParkingSpace parkingSpace;

    @OneToOne(mappedBy = "car")
    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public CarEntity setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
        return this;
    }

    public void exitParking() {
        this.calculateTotalPrice(HOURLY_PRICE, WHOLE_DAY_PRICE);
    }


}
