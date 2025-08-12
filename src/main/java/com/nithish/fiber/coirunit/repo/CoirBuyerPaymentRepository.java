package com.nithish.fiber.coirunit.repo;


import com.nithish.fiber.coirunit.entity.CoirBuyerPayment;
import com.nithish.fiber.coirunit.entity.CoirWorkerAdvance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoirBuyerPaymentRepository extends JpaRepository<CoirBuyerPayment, Long> { }
