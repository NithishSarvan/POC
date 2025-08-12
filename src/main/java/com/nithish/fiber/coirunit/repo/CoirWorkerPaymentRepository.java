package com.nithish.fiber.coirunit.repo;


import com.nithish.fiber.coirunit.entity.CoirWorkerAdvance;
import com.nithish.fiber.coirunit.entity.CoirWorkerPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoirWorkerPaymentRepository extends JpaRepository<CoirWorkerPayment, Long> { }
