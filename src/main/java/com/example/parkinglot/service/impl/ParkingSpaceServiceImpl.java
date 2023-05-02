package com.example.parkinglot.service.impl;

import com.example.parkinglot.model.entity.CarEntity;
import com.example.parkinglot.model.entity.ParkingSpace;
import com.example.parkinglot.model.enums.ParkingSpaceStatus;
import com.example.parkinglot.model.enums.VehicleType;
import com.example.parkinglot.repository.ParkingSpaceRepository;
import com.example.parkinglot.service.ParkingSpaceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ParkingSpaceServiceImpl implements ParkingSpaceService {
    private static final int MAX_CAR_SPACES = 50;
    private static final int MAX_BUS_SPACES = 10;
    private final ParkingSpaceRepository parkingSpaceRepository;

    public ParkingSpaceServiceImpl(ParkingSpaceRepository parkingSpaceRepository) {
        this.parkingSpaceRepository = parkingSpaceRepository;
    }

    @Override
    public boolean ensureCapacity(VehicleType vehicleType) {
        List<ParkingSpace> parkingSpaces;
        int maxSpaces;

        switch (vehicleType.name()) {
            case "CAR" -> {
                parkingSpaces = this.parkingSpaceRepository.findAllByCarIsNotNullAndStatus(ParkingSpaceStatus.OCCUPIED);
                maxSpaces = MAX_CAR_SPACES;
            }
            case "BUS" -> {
                parkingSpaces = this.parkingSpaceRepository.findAllByBusIsNotNullAndStatus(ParkingSpaceStatus.OCCUPIED);
                maxSpaces = MAX_BUS_SPACES;
            }
            default -> throw new NoSuchElementException("There is now such vehicle type");
        }

        return parkingSpaces.size() < maxSpaces;
    }

    @Override
    public void occupyParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpaceRepository.save(parkingSpace);
    }
}
