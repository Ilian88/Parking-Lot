package com.example.parkinglot.service;

import com.example.parkinglot.model.dto.EnterVehicleDTO;
import com.example.parkinglot.model.entity.VehicleEntity;

public interface VehicleService {
    void vehicleEnters(EnterVehicleDTO vehicle);

    void vehicleExits(String licensePlate);

    VehicleEntity getVehicleBySaleId(long saleId);
}
