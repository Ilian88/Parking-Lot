package com.example.parkinglot.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Map;

@Table
@Entity(name = "parking-lot")
public class ParkingLotEntity extends BaseEntity {
    private Map<String, CarEntity> carsInLot;
    private Map<String, BusEntity> busesInLot;
}
