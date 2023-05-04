package com.example.parkinglot.service;

import com.example.parkinglot.model.entity.Sale;
import com.example.parkinglot.model.view.SaleReportDTO;
import com.example.parkinglot.model.view.SaleRevenueDTO;

import java.time.LocalDate;
import java.util.List;

public interface SaleService {
    void saveSale(Sale sale);
    List<SaleReportDTO> getSalesInTimeRange(String fromDate, String toDate);
    List<SaleRevenueDTO> getDayRevenuePerMonth(int month, int year);

}
