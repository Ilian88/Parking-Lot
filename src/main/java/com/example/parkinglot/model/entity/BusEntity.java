package com.example.parkinglot.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "buses")
public class BusEntity extends VehicleEntity {
    private static final int HOURLY_PRICE = 5;
    private static final int WHOLE_DAY_PRICE = 50;

    public void exitParking() {
        this.calculateTotalPrice(HOURLY_PRICE, WHOLE_DAY_PRICE);
    }
}
