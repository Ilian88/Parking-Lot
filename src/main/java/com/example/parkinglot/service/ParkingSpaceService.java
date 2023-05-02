package com.example.parkinglot.service;

import com.example.parkinglot.model.entity.ParkingSpace;
import com.example.parkinglot.model.enums.VehicleType;

public interface ParkingSpaceService {
    boolean ensureCapacity(VehicleType vehicleType);
    void occupyParkingSpace(ParkingSpace vehicle);
}
