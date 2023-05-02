//package com.example.parkinglot.service.helper;
//
//import com.example.parkinglot.model.dto.EnterVehicleDTO;
//import com.example.parkinglot.model.entity.BusEntity;
//import com.example.parkinglot.model.entity.CarEntity;
//import com.example.parkinglot.model.entity.VehicleEntity;
//
//import java.time.LocalDateTime;
//import java.util.NoSuchElementException;
//
//public class VehicleHelper {
//
//    public static VehicleEntity createVehicle(EnterVehicleDTO vehicleDTO) {
//        VehicleEntity vehicle;
//
//        switch (String.valueOf(vehicleDTO.getVehicleType()).toUpperCase()) {
//            case "CAR" -> vehicle = new CarEntity()
//                    .setLicencePlate(vehicleDTO.getLicencePlate())
//                    .setEnteredAt(LocalDateTime.now());
//            case "BUS" -> vehicle = new BusEntity()
//                    .setLicencePlate(vehicleDTO.getLicencePlate())
//                    .setEnteredAt(LocalDateTime.now());
//            default -> throw new NoSuchElementException("There is no such vehicle type");
//        }
//
//        return vehicle;
//    }
//}
