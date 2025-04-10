package com.kali.fiber.controller;

import com.kali.fiber.entity.LaborWages;
import com.kali.fiber.service.LaborWagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/labor-wages")
public class LaborWagesController {
    private final LaborWagesService laborWagesService;

    @Autowired
    public LaborWagesController(LaborWagesService laborWagesService) {
        this.laborWagesService = laborWagesService;
    }

    @PostMapping("/hourly")
    public LaborWages calculateHourlyWage(@RequestParam Long laborId, @RequestParam double hoursWorked) {
        return laborWagesService.calculateHourlyWage(laborId, hoursWorked);
    }

    @PostMapping("/block")
    public LaborWages calculateBlockWage(@RequestParam Long laborId, @RequestParam int blocksCompleted) {
        return laborWagesService.calculateBlockWage(laborId, blocksCompleted);
    }

    @PostMapping("/{id}/advance")
    public LaborWages recordAdvancePayment(@PathVariable Long id, @RequestParam BigDecimal advanceAmount) {
        return laborWagesService.recordAdvancePayment(id, advanceAmount);
    }

    @GetMapping("/labor/{laborId}")
    public List<LaborWages> getWagesByLabor(@PathVariable Long laborId) {
        return laborWagesService.getWagesByLabor(laborId);
    }
}
