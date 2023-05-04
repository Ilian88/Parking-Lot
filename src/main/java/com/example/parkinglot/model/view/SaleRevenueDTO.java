package com.example.parkinglot.model.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SaleRevenueDTO {
    private LocalDate date;
    private BigDecimal dayRevenue;

    public SaleRevenueDTO() {
    }

    public LocalDate getDate() {
        return date;
    }

    public SaleRevenueDTO setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public BigDecimal getDayRevenue() {
        return dayRevenue;
    }

    public SaleRevenueDTO setDayRevenue(BigDecimal dayRevenue) {
        this.dayRevenue = dayRevenue;
        return this;
    }
}
