package com.example.parkinglot.repository;

import com.example.parkinglot.model.entity.ParkingSpace;
import com.example.parkinglot.model.enums.ParkingSpaceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
    List<ParkingSpace> findAllByCarIsNotNullAndStatus (ParkingSpaceStatus parkingSpaceStatus);
    List<ParkingSpace> findAllByBusIsNotNullAndStatus (ParkingSpaceStatus parkingSpaceStatus);
}
