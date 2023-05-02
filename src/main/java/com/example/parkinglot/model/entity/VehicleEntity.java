package com.example.parkinglot.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@MappedSuperclass
public abstract class VehicleEntity extends BaseEntity {

    public VehicleEntity() {
    }

    protected String licencePlate;

    protected LocalDateTime enteredAt;

    protected LocalDateTime leftAt;

    protected BigDecimal totalPrice;

    @Column(name = "license_plate", nullable = false)
    @Size(min = 4)
    public String getLicencePlate() {
        return licencePlate;
    }

    public VehicleEntity setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
        return this;
    }

    @Column(name = "entered_at", nullable = false)
    public LocalDateTime getEnteredAt() {
        return enteredAt;
    }

    public VehicleEntity setEnteredAt(LocalDateTime enteredAt) {
        this.enteredAt = enteredAt;
        return this;
    }

    @Column(name = "left_at")
    public LocalDateTime getLeftAt() {
        return leftAt;
    }

    public VehicleEntity setLeftAt(LocalDateTime leftAt) {
        this.leftAt = leftAt;
        return this;
    }

    @Column(name = "total_price")
    @Positive
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public VehicleEntity setTotalPrice(BigDecimal totalSum) {
        this.totalPrice = totalSum;
        return this;
    }

    protected void calculateTotalPrice(int hourlyPrice, int wholeDayPrice) {
        long diff = this.calculateHoursSpent();

        if (diff < 24) {
            this.totalPrice = BigDecimal.valueOf(diff * hourlyPrice);

        } else {
            long days = diff / 24;
            long hours = diff % 24;

            this.totalPrice = BigDecimal.valueOf((days * wholeDayPrice) + (hours * hourlyPrice));
        }
    };

    public long calculateHoursSpent() {
        return ChronoUnit.HOURS.between(enteredAt, leftAt);
    }

}
