package com.example.parkinglot.service.helper;

import com.example.parkinglot.model.entity.Sale;
import com.example.parkinglot.model.entity.VehicleEntity;
import com.example.parkinglot.model.enums.VehicleType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class SaleCreator {
    private static int CAR_HOUR_PRICE = 1;
    private static int CAR_WHOLE_DAY_PRICE = 10;
    private static int BUS_HOUR_PRICE = 5;
    private static int BUS_WHOLE_DAY_PRICE = 40;

    public Sale createSale(VehicleEntity vehicle) {
        Sale sale = new Sale();
        sale.setVehicle(vehicle);

        long hoursSpent = this.calculateHoursSpent(vehicle.getEnteredAt(), vehicle.getLeftAt());
        BigDecimal totalPrice = this.calculateTotalPrice(hoursSpent, vehicle.getVehicleType());

        sale
                .setHoursSpent(hoursSpent)
                .setPrice(totalPrice)
                .setCreatedOn(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        return sale;
    }
    private BigDecimal calculateTotalPrice(long hoursSpent, VehicleType vehicleType) {
        int hourlyPrice = 0;
        int wholeDayPrice = 0;

        switch (vehicleType) {
            case CAR -> {
                hourlyPrice = CAR_HOUR_PRICE;
                wholeDayPrice = CAR_WHOLE_DAY_PRICE;
            }
            case BUS -> {
                hourlyPrice = BUS_HOUR_PRICE;
                wholeDayPrice = BUS_WHOLE_DAY_PRICE;
            }
        }

        if (hoursSpent < 24) {
            return BigDecimal.valueOf(hoursSpent * hourlyPrice);

        } else {
            long days = hoursSpent / 24;
            long hours = hoursSpent % 24;

            return BigDecimal.valueOf((days * wholeDayPrice) + (hours * hourlyPrice));
        }
    }
    public long calculateHoursSpent(LocalDateTime enteredAt, LocalDateTime leftAt) {
        return ChronoUnit.HOURS.between(enteredAt, leftAt) + 1;
    }
}
