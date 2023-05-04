package com.example.parkinglot.model.view;

import com.example.parkinglot.model.enums.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SaleReportDTO {
    private VehicleType vehicleType;

    private LocalDateTime enteredAt;

    private LocalDateTime leftAt;

    private long hoursCharged;

    private BigDecimal price;

    public SaleReportDTO() {
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public SaleReportDTO setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public LocalDateTime getEnteredAt() {
        return enteredAt;
    }

    public SaleReportDTO setEnteredAt(LocalDateTime enteredAt) {
        this.enteredAt = enteredAt;
        return this;
    }

    public LocalDateTime getLeftAt() {
        return leftAt;
    }

    public SaleReportDTO setLeftAt(LocalDateTime leftAt) {
        this.leftAt = leftAt;
        return this;
    }

    public long getHoursCharged() {
        return hoursCharged;
    }

    public SaleReportDTO setHoursCharged(long hoursCharged) {
        this.hoursCharged = hoursCharged;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public SaleReportDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
