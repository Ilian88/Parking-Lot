package com.example.parkinglot.model.entity;

import com.example.parkinglot.model.enums.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table
@Entity(name = "sales")
public class Sale extends BaseEntity {

    private String licencePlate;

    private long timeSpent;

    private VehicleType vehicleType;

    private BigDecimal totalSum;

    private LocalDateTime dateOfSale;
    public Sale() {
    }

    @Column(name = "license_plate", nullable = false)
    @Size(min = 4)
    public String getLicencePlate() {
        return licencePlate;
    }

    public Sale setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
        return this;
    }

    @Column(name = "time_spent", nullable = false)
    @Positive
    public long getTimeSpent() {
        return timeSpent;
    }

    public Sale setTimeSpent(long timeSpent) {
        this.timeSpent = timeSpent;
        return this;
    }

    @Column(name = "total_sum")
    @Positive
    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public Sale setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
        return this;
    }

    @Column(name = "date_of_sale")
    public LocalDateTime getDateOfSale() {
        return dateOfSale;
    }

    public Sale setDateOfSale(LocalDateTime dateOfSale) {
        this.dateOfSale = dateOfSale;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Sale setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }
}
