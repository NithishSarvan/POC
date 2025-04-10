package com.kali.fiber.service;

import com.kali.fiber.entity.Labor;
import com.kali.fiber.entity.LaborWages;
import com.kali.fiber.repository.LaborRepository;
import com.kali.fiber.repository.LaborWagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class LaborWagesService {
    private final LaborWagesRepository laborWagesRepository;
    private final LaborRepository laborRepository;

    @Autowired
    public LaborWagesService(LaborWagesRepository laborWagesRepository, LaborRepository laborRepository) {
        this.laborWagesRepository = laborWagesRepository;
        this.laborRepository = laborRepository;
    }

    public LaborWages calculateHourlyWage(Long laborId, double hoursWorked) {
        Labor labor = laborRepository.findById(laborId).orElse(null);
        if (labor == null) {
            throw new RuntimeException("Labor not found");
        }

        if (labor.getHourlyRate() == null) {
            throw new RuntimeException("Hourly rate not set for this labor");
        }

        LaborWages wages = new LaborWages();
        wages.setLabor(labor);
        wages.setCalculationType(LaborWages.CalculationType.HOURLY);
        wages.setHoursWorked(hoursWorked);
        wages.setAmount(labor.getHourlyRate().multiply(BigDecimal.valueOf(hoursWorked)));
        wages.setAdvancePaid(BigDecimal.ZERO);
        wages.setPaymentStatus(LaborWages.PaymentStatus.PENDING);
        wages.setCreatedAt(new Date());
        wages.setUpdatedAt(new Date());

        return laborWagesRepository.save(wages);
    }

    public LaborWages calculateBlockWage(Long laborId, int blocksCompleted) {
        Labor labor = laborRepository.findById(laborId).orElse(null);
        if (labor == null) {
            throw new RuntimeException("Labor not found");
        }

        if (labor.getBlockRate() == null) {
            throw new RuntimeException("Block rate not set for this labor");
        }

        LaborWages wages = new LaborWages();
        wages.setLabor(labor);
        wages.setCalculationType(LaborWages.CalculationType.BLOCK);
        wages.setBlocksCompleted(blocksCompleted);
        wages.setAmount(labor.getBlockRate().multiply(BigDecimal.valueOf(blocksCompleted)));
        wages.setAdvancePaid(BigDecimal.ZERO);
        wages.setPaymentStatus(LaborWages.PaymentStatus.PENDING);
        wages.setCreatedAt(new Date());
        wages.setUpdatedAt(new Date());

        return laborWagesRepository.save(wages);
    }

    public LaborWages recordAdvancePayment(Long wagesId, BigDecimal advanceAmount) {
        LaborWages wages = laborWagesRepository.findById(wagesId).orElse(null);
        if (wages == null) {
            throw new RuntimeException("Wages record not found");
        }

        BigDecimal newAdvance = wages.getAdvancePaid().add(advanceAmount);
        if (newAdvance.compareTo(wages.getAmount()) > 0) {
            throw new RuntimeException("Advance payment cannot exceed total amount");
        }

        wages.setAdvancePaid(newAdvance);
        wages.setPaymentStatus(newAdvance.compareTo(wages.getAmount()) == 0 ?
                LaborWages.PaymentStatus.PAID :
                (newAdvance.compareTo(BigDecimal.ZERO) > 0 ?
                        LaborWages.PaymentStatus.PARTIAL :
                        LaborWages.PaymentStatus.PENDING));
        wages.setUpdatedAt(new Date());

        return laborWagesRepository.save(wages);
    }

    public List<LaborWages> getWagesByLabor(Long laborId) {
        return laborWagesRepository.findByLaborId(laborId);
    }
}