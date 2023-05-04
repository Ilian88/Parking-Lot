package com.example.parkinglot.service.impl;

import com.example.parkinglot.model.entity.Sale;
import com.example.parkinglot.model.entity.VehicleEntity;
import com.example.parkinglot.model.view.SaleReportDTO;
import com.example.parkinglot.model.view.SaleRevenueDTO;
import com.example.parkinglot.repository.SaleRepository;
import com.example.parkinglot.service.SaleService;
import com.example.parkinglot.service.VehicleService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd";
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    private final SaleRepository saleRepository;
    private final VehicleService vehicleService;

    public SaleServiceImpl(SaleRepository saleRepository,@Lazy VehicleService vehicleService) {
        this.saleRepository = saleRepository;
        this.vehicleService = vehicleService;
    }

    @Override
    public void saveSale(Sale sale) {
        this.saleRepository.save(sale);
    }

    @Override
    public List<SaleReportDTO> getSalesInTimeRange(String fromDate, String toDate) {
        LocalDate fromDateParsed = LocalDate.parse(fromDate, this.dateTimeFormatter);
        LocalDate toDateParsed = LocalDate.parse(toDate, this.dateTimeFormatter);

        return this.saleRepository.findByCreatedOnBetween(fromDateParsed.atStartOfDay(), toDateParsed.atStartOfDay())
                .stream()
                .map(sale -> {
                    VehicleEntity vehicle = this.vehicleService.getVehicleBySaleId(sale.getId());

                    return buildSaleReportDTO(sale, vehicle);
                })
                .toList();
    }

    @Override
    public List<SaleRevenueDTO> getDayRevenuePerMonth(int month, int year) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = LocalDate.of(year, month + 1, 1);

        List<Sale> sales = this.saleRepository.findByCreatedOnBetween(start.atStartOfDay(), end.atStartOfDay());
        List<SaleRevenueDTO> saleRevenue = new ArrayList<>();

        LocalDate current = LocalDate.of(year, month, 1);

        while (current.getMonth().getValue() == month) {
            LocalDate finalCurrent = current;

            BigDecimal value = sales.stream()
                    .filter(sale -> sale.getCreatedOn().getDayOfMonth() == finalCurrent.getDayOfMonth())
                    .map(Sale::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            if (!value.equals(BigDecimal.ZERO)) {
                SaleRevenueDTO saleRevenueDTO = new SaleRevenueDTO();
                saleRevenueDTO
                        .setDayRevenue(value)
                        .setDate(current);

                saleRevenue.add(saleRevenueDTO);
            }

            current = current.plusDays(1);
        }

        return saleRevenue;
    }

    private SaleReportDTO buildSaleReportDTO(Sale sale, VehicleEntity vehicle) {
        SaleReportDTO saleReportDTO = new SaleReportDTO();

        return saleReportDTO
                .setLicensePlate(vehicle.getLicensePlate())
                .setEnteredAt(vehicle.getEnteredAt())
                .setLeftAt(vehicle.getLeftAt())
                .setVehicleType(vehicle.getVehicleType())
                .setHoursCharged(sale.getHoursToCharge())
                .setPrice(sale.getPrice());

    }
}
