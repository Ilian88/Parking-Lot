package com.example.parkinglot.service;

import com.example.parkinglot.model.dto.EnterVehicleDTO;

public interface VehicleService {
    Void vehicleEnters(EnterVehicleDTO vehicle);
}
