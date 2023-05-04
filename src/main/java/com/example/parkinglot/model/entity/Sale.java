package com.example.parkinglot.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table
@Entity(name = "sales")
public class Sale extends BaseEntity {
    private VehicleEntity vehicle;
    private BigDecimal price;
    private LocalDateTime createdOn;
    private long hoursToCharge;

    public Sale() {
    }

    @OneToOne
    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public Sale setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    @Column(name = "price")
    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public Sale setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Column(name = "hours_charged")
    @Positive
    public long getHoursToCharge() {
        return hoursToCharge;
    }

    public Sale setHoursToCharge(long hoursToCharge) {
        this.hoursToCharge = hoursToCharge;
        return this;
    }

    @Column(name = "created_on")
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public Sale setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }
}
