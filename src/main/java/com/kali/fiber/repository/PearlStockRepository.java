package com.kali.fiber.repository;


import com.kali.fiber.entity.PearlStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PearlStockRepository extends JpaRepository<PearlStock, Long> {
}