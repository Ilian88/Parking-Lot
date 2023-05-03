package com.example.parkinglot.service.impl;

import com.example.parkinglot.exceptions.ParkingFullException;
import com.example.parkinglot.model.entity.ParkingSpace;
import com.example.parkinglot.model.enums.ParkingSpaceStatus;
import com.example.parkinglot.model.enums.VehicleType;
import com.example.parkinglot.repository.ParkingSpaceRepository;
import com.example.parkinglot.service.ParkingSpaceService;
import org.springframework.stereotype.Service;

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
    public ParkingSpace getByVehicleId(long vehicleId) {
        return this.parkingSpaceRepository.findByVehicleId(vehicleId);
    }

    @Override
    public void ensureCapacity(VehicleType vehicleType) {
        int maxSpaces;

        switch (vehicleType.name()) {
            case "CAR" -> maxSpaces = MAX_CAR_SPACES;

            case "BUS" -> maxSpaces = MAX_BUS_SPACES;

            default -> throw new NoSuchElementException("There is now such vehicle type");
        }

        List<ParkingSpace> parkingSpaces = this.parkingSpaceRepository
                .findAllByVehicleIsNotNullAndStatus(ParkingSpaceStatus.OCCUPIED);

        if (parkingSpaces.size() == maxSpaces) {
            throw new ParkingFullException("Parking is full. Please try again later");
        };
    }

    @Override
    public void occupyParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpaceRepository.save(parkingSpace);
    }

    @Override
    public void save(ParkingSpace parkingSpace) {
        this.parkingSpaceRepository.save(parkingSpace);
    }
}
