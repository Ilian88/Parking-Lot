package com.example.parkinglot.service.impl;

import com.example.parkinglot.model.entity.Sale;
import com.example.parkinglot.repository.SaleRepository;
import com.example.parkinglot.service.SaleService;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;

    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public void saveSale(Sale sale) {
        this.saleRepository.save(sale);
    }
}
