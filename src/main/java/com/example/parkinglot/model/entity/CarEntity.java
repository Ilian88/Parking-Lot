package com.example.parkinglot.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class CarEntity extends VehicleEntity {
    private static final int HOURLY_PRICE = 1;
    private static final int WHOLE_DAY_PRICE = 10;

    public void exitParking() {
        this.calculateTotalPrice(HOURLY_PRICE, WHOLE_DAY_PRICE);
    }
}
