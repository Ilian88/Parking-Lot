package com.example.parkinglot.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@MappedSuperclass
public abstract class VehicleEntity extends BaseEntity {

    public VehicleEntity() {
    }

    @Column(name = "license-plate", nullable = false)
    @Size(min = 4)
    protected String licencePlate;
    @Column(name = "entered-at", nullable = false)
    protected LocalDateTime enteredAt;
    @Column(name = "left-at")
    protected LocalDateTime leftAt;

    @Column(name = "total-sum")
    protected BigDecimal totalPrice;

    protected String getLicencePlate() {
        return licencePlate;
    }

    protected VehicleEntity setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
        return this;
    }

    protected LocalDateTime getEnteredAt() {
        return enteredAt;
    }

    protected VehicleEntity setEnteredAt(LocalDateTime enteredAt) {
        this.enteredAt = enteredAt;
        return this;
    }

    protected LocalDateTime getLeftAt() {
        return leftAt;
    }

    protected VehicleEntity setLeftAt(LocalDateTime leftAt) {
        this.leftAt = leftAt;
        return this;
    }

    protected BigDecimal getTotalPrice() {
        return totalPrice;
    }

    protected VehicleEntity setTotalPrice(BigDecimal totalSum) {
        this.totalPrice = totalSum;
        return this;
    }

    protected void calculateTotalPrice(int hourlyPrice, int wholeDayPrice) {
        long diff = ChronoUnit.MINUTES.between(enteredAt, leftAt);

        if (diff < 24) {
            this.totalPrice = BigDecimal.valueOf(diff * hourlyPrice);

        } else {
            long days = diff / 24;
            long minutes = diff % 24;

            this.totalPrice = BigDecimal.valueOf((days * wholeDayPrice) + (minutes * hourlyPrice));
        }
    };

}
