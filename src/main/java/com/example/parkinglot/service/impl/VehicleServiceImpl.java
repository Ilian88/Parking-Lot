package com.example.parkinglot.service.impl;

import com.example.parkinglot.model.dto.EnterVehicleDTO;
import com.example.parkinglot.model.entity.BusEntity;
import com.example.parkinglot.model.entity.CarEntity;
import com.example.parkinglot.model.entity.ParkingSpace;
import com.example.parkinglot.model.enums.ParkingSpaceStatus;
import com.example.parkinglot.model.enums.VehicleType;
import com.example.parkinglot.repository.BusRepository;
import com.example.parkinglot.repository.CarRepository;
import com.example.parkinglot.service.ParkingSpaceService;
import com.example.parkinglot.service.VehicleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final CarRepository carRepository;
    private final BusRepository busRepository;

    private final ParkingSpaceService parkingSpaceService;

    public VehicleServiceImpl(CarRepository carRepository, BusRepository busRepository, ParkingSpaceService parkingSpaceService) {
        this.carRepository = carRepository;
        this.busRepository = busRepository;
        this.parkingSpaceService = parkingSpaceService;
    }

    @Override
    public Void vehicleEnters(EnterVehicleDTO vehicleDto) {

        switch (vehicleDto.getVehicleType().toUpperCase()) {
            case "CAR" -> {
                CarEntity car = (CarEntity) new CarEntity()
                        .setLicencePlate(vehicleDto.getLicencePlate())
                        .setEnteredAt(LocalDateTime.now());


                this.carRepository.save(car);

                ParkingSpace parkingSpace = new ParkingSpace();
                parkingSpace.setCar(car);
                parkingSpace.setStatus(ParkingSpaceStatus.OCCUPIED);

                this.parkingSpaceService.ensureCapacity(VehicleType.CAR);
                this.parkingSpaceService.occupyParkingSpace(parkingSpace);
            }

            case "BUS" -> {
                BusEntity bus = (BusEntity) new BusEntity()
                        .setLicencePlate(vehicleDto.getLicencePlate())
                        .setEnteredAt(LocalDateTime.now());

                this.busRepository.save(bus);

                ParkingSpace parkingSpace = new ParkingSpace();
                parkingSpace.setBus(bus);
                parkingSpace.setStatus(ParkingSpaceStatus.OCCUPIED);

                this.parkingSpaceService.occupyParkingSpace(parkingSpace);
            }
            default -> throw new NoSuchElementException("There is no such vehicle type");
        }

        return null;
    }
}
