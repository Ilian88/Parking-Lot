package com.example.parkinglot.service;

import com.example.parkinglot.model.dto.EnterVehicleDTO;

public interface VehicleService {
    void vehicleEnters(EnterVehicleDTO vehicle);

    void vehicleExits(String licensePlate);
}
