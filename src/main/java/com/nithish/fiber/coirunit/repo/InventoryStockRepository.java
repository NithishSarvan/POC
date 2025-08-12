package com.nithish.fiber.coirunit.repo;


import com.nithish.fiber.coirunit.entity.CoirWorkerPayment;
import com.nithish.fiber.coirunit.entity.InventoryStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryStockRepository extends JpaRepository<InventoryStock, Long> { }
