package com.example.parkinglot.repository;

import com.example.parkinglot.model.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByCreatedOnBetween(LocalDateTime from, LocalDateTime to);
}
