package com.nithish.fiber.coirunit.repo;


import com.nithish.fiber.coirunit.entity.CoirBuyer;
import com.nithish.fiber.coirunit.entity.CoirBuyerPaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoirBuyerPaymentHistoryRepository extends JpaRepository<CoirBuyerPaymentHistory, Long> { }
