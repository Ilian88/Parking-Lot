package com.example.parkinglot.service.helper;

import com.example.parkinglot.model.entity.Sale;
import com.example.parkinglot.model.entity.VehicleEntity;
import com.example.parkinglot.model.enums.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class  SaleCreatorTest {
    SaleCreator saleCreator = new SaleCreator();
    LocalDateTime entered;
    LocalDateTime left;
    @BeforeEach
    void init() {
        entered  = LocalDateTime.of(LocalDate.of(2023, 5, 5), LocalTime.of(12, 35));
        left = LocalDateTime.of(LocalDate.of(2023, 5, 6), LocalTime.of(12, 50));
    }

    @Test
    void testCreateSaleForCar() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setLicensePlate("BP0506CX")
                .setEnteredAt(entered)
                .setLeftAt(left)
                .setVehicleType(VehicleType.CAR);

        Sale sale = saleCreator.createSale(vehicle);

        assertEquals(sale.getCreatedOn().truncatedTo(ChronoUnit.MINUTES), LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        assertEquals(sale.getPrice(), BigDecimal.valueOf(11));
        assertEquals(sale.getVehicle().getVehicleType(), VehicleType.CAR);
    }

    @Test
    void testCreateSaleForBus() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setLicensePlate("BP1234CB")
                .setEnteredAt(entered)
                .setLeftAt(left)
                .setVehicleType(VehicleType.CAR);

        Sale sale = saleCreator.createSale(vehicle);

        assertEquals(sale.getCreatedOn().truncatedTo(ChronoUnit.MINUTES), LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        assertEquals(sale.getPrice(), BigDecimal.valueOf(11));
        assertEquals(sale.getVehicle().getVehicleType(), VehicleType.CAR);
    }

    @Test
    void testCalculateHoursToCharge() {
        assertEquals(25L, saleCreator.calculatehoursToCharge(entered, left));
        assertEquals(saleCreator.calculatehoursToCharge(entered, left.plusDays(1).plusHours(3)), 52L);
    }
}
