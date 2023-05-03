package com.example.parkinglot.repository;

import com.example.parkinglot.model.entity.VehicleEntity;
import com.example.parkinglot.model.enums.ParkingSpaceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
    Optional<VehicleEntity> findByLicensePlateAndParkingSpaceStatus(String licensePlate, ParkingSpaceStatus parkingSpaceStatus);

    boolean existsByLicensePlate(String licensePlate);
}
