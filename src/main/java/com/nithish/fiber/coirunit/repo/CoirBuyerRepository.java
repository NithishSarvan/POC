package com.nithish.fiber.coirunit.repo;


import com.nithish.fiber.coirunit.entity.CoirBuyer;
import com.nithish.fiber.coirunit.entity.CoirWorkerPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoirBuyerRepository extends JpaRepository<CoirBuyer, Long> { }
