package com.example.parkinglot.controller;

import com.example.parkinglot.model.view.SaleReportDTO;
import com.example.parkinglot.model.view.SaleRevenueDTO;
import com.example.parkinglot.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<SaleReportDTO>> getSales(@RequestParam String fromDate, @RequestParam String toDate) {
        List<SaleReportDTO> sales = this.saleService.getSalesInTimeRange(fromDate, toDate);

        return ResponseEntity.ok().body(sales);
    }

    @GetMapping("/revenue")
    public ResponseEntity<List<SaleRevenueDTO>> getDayRevenue(@RequestParam int month, @RequestParam int year) {

        List<SaleRevenueDTO> salesRevenues = this.saleService.getDayRevenuePerMonth(month, year);

        return ResponseEntity.ok().body(salesRevenues);
    }
}
