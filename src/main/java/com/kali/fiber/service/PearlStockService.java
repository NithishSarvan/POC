package com.kali.fiber.service;

import com.kali.fiber.entity.PearlStock;
import com.kali.fiber.repository.PearlStockRepository;
import com.kali.fiber.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PearlStockService {
    private final PearlStockRepository pearlStockRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public PearlStockService(PearlStockRepository pearlStockRepository, SupplierRepository supplierRepository) {
        this.pearlStockRepository = pearlStockRepository;
        this.supplierRepository = supplierRepository;
    }

    public List<PearlStock> getAllPearlStocks() {
        return pearlStockRepository.findAll();
    }

    public PearlStock getPearlStockById(Long id) {
        return pearlStockRepository.findById(id).orElse(null);
    }

    public PearlStock createPearlStock(PearlStock pearlStock) {
        return pearlStockRepository.save(pearlStock);
    }

    public PearlStock updatePearlStock(Long id, PearlStock pearlStockDetails) {
        PearlStock pearlStock = pearlStockRepository.findById(id).orElse(null);
        if (pearlStock != null) {
            pearlStock.setSupplier(pearlStockDetails.getSupplier());
            pearlStock.setVehicleDetails(pearlStockDetails.getVehicleDetails());
            pearlStock.setPurchaseDate(pearlStockDetails.getPurchaseDate());
            pearlStock.setQuantity(pearlStockDetails.getQuantity());
            pearlStock.setAdvanceAmount(pearlStockDetails.getAdvanceAmount());
            pearlStock.setTotalAmount(pearlStockDetails.getTotalAmount());
            pearlStock.setStockType(pearlStockDetails.getStockType());
            pearlStock.setIsDry(pearlStockDetails.getIsDry());
            return pearlStockRepository.save(pearlStock);
        }
        return null;
    }

    public void deletePearlStock(Long id) {
        pearlStockRepository.deleteById(id);
    }
}
