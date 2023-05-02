package com.example.parkinglot.controller;

import com.example.parkinglot.model.dto.EnterVehicleDTO;
import com.example.parkinglot.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/enter")
    public ResponseEntity<Void> carEnter(@RequestBody @Valid EnterVehicleDTO enterVehicle) {
        this.vehicleService.vehicleEnters(enterVehicle);

        return ResponseEntity.ok().build();
    }
}
