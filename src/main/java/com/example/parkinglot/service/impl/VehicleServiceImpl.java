package com.example.parkinglot.service.impl;

import com.example.parkinglot.exceptions.ElementAlreadyExistsException;
import com.example.parkinglot.exceptions.InvalidVehicleTypeException;
import com.example.parkinglot.model.dto.EnterVehicleDTO;
import com.example.parkinglot.model.entity.ParkingSpace;
import com.example.parkinglot.model.entity.Sale;
import com.example.parkinglot.model.entity.VehicleEntity;
import com.example.parkinglot.model.enums.ParkingSpaceStatus;
import com.example.parkinglot.model.enums.VehicleType;
import com.example.parkinglot.repository.VehicleRepository;
import com.example.parkinglot.service.ParkingSpaceService;
import com.example.parkinglot.service.SaleService;
import com.example.parkinglot.service.VehicleService;
import com.example.parkinglot.service.helper.SaleCreator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final SaleService saleService;
    private final ParkingSpaceService parkingSpaceService;
    private final SaleCreator saleCreator;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, SaleService saleService,
                              ParkingSpaceService parkingSpaceService, SaleCreator saleCreator) {
        this.vehicleRepository = vehicleRepository;
        this.saleService = saleService;
        this.parkingSpaceService = parkingSpaceService;
        this.saleCreator = saleCreator;
    }


    @Override
    public void vehicleEnters(EnterVehicleDTO vehicleDto) {
        checkIfVehicleAlreadyExists(vehicleDto.getlicensePlate());
        checkIfValidOrAllowedVehcicleType(vehicleDto.getVehicleType());

        VehicleType vehicleType = VehicleType.valueOf(vehicleDto.getVehicleType().toUpperCase());

        parkingSpaceService.ensureCapacity(vehicleType);

        VehicleEntity vehicle = new VehicleEntity()
                .setLicensePlate(vehicleDto.getlicensePlate())
                .setEnteredAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .setVehicleType(vehicleType);

        this.vehicleRepository.save(vehicle);

        ParkingSpace parkingSpace = new ParkingSpace();

        parkingSpace.setStatus(ParkingSpaceStatus.OCCUPIED);
        parkingSpace.setVehicle(vehicle);

        this.parkingSpaceService.occupyParkingSpace(parkingSpace);
    }

    @Override
    public void vehicleExits(String licensePlate) {
        VehicleEntity vehicle = this.vehicleRepository.findByLicensePlateAndParkingSpaceStatus(licensePlate, ParkingSpaceStatus.OCCUPIED)
                .orElseThrow(()-> new NoSuchElementException("There is currently no vehicle in parking with this plate number!"));
        vehicle.setLeftAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        Sale sale = this.saleCreator.createSale(vehicle);
        ParkingSpace parkingSpace = this.parkingSpaceService.getByVehicleId(vehicle.getId());
        parkingSpace.setStatus(ParkingSpaceStatus.COMPLETED);

        this.parkingSpaceService.save(parkingSpace);
        this.vehicleRepository.save(vehicle);
        this.saleService.saveSale(sale);
    }

    @Override
    public VehicleEntity getVehicleBySaleId(long saleId) {
        return this.vehicleRepository.findBySaleId(saleId);
    }

    private void checkIfVehicleAlreadyExists(String licensePlate) {
        boolean exists = this.vehicleRepository.existsByLicensePlate(licensePlate);

        if (exists) {
            throw new ElementAlreadyExistsException("Vehcile with that license plate already exists");
        }
    }

    private void checkIfValidOrAllowedVehcicleType(String vehicleType) {
        if (!vehicleType.equalsIgnoreCase("CAR") && !vehicleType.equalsIgnoreCase("BUS")) {
            throw new InvalidVehicleTypeException("Allowed vehicles are either CAR or BUS");
        }
    }
}
