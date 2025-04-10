package com.kali.fiber.controller;

import com.kali.fiber.entity.PearlStock;
import com.kali.fiber.service.PearlStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pearl-stocks")
public class PearlStockController {
    private final PearlStockService pearlStockService;

    @Autowired
    public PearlStockController(PearlStockService pearlStockService) {
        this.pearlStockService = pearlStockService;
    }

    @GetMapping
    public List<PearlStock> getAllPearlStocks() {
        return pearlStockService.getAllPearlStocks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PearlStock> getPearlStockById(@PathVariable Long id) {
        PearlStock pearlStock = pearlStockService.getPearlStockById(id);
        return pearlStock != null ? ResponseEntity.ok(pearlStock) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public PearlStock createPearlStock(@RequestBody PearlStock pearlStock) {
        return pearlStockService.createPearlStock(pearlStock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PearlStock> updatePearlStock(@PathVariable Long id, @RequestBody PearlStock pearlStockDetails) {
        PearlStock updatedPearlStock = pearlStockService.updatePearlStock(id, pearlStockDetails);
        return updatedPearlStock != null ? ResponseEntity.ok(updatedPearlStock) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePearlStock(@PathVariable Long id) {
        pearlStockService.deletePearlStock(id);
        return ResponseEntity.noContent().build();
    }
}
