package com.kali.fiber.service;

import com.kali.fiber.entity.*;
import com.kali.fiber.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryItemRepository deliveryItemRepository;
    private final ClientRepository clientRepository;
    private final PearlStockRepository pearlStockRepository;
    private final InventoryLogRepository inventoryLogRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository,
                           DeliveryItemRepository deliveryItemRepository,
                           ClientRepository clientRepository,
                           PearlStockRepository pearlStockRepository,
                           InventoryLogRepository inventoryLogRepository) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryItemRepository = deliveryItemRepository;
        this.clientRepository = clientRepository;
        this.pearlStockRepository = pearlStockRepository;
        this.inventoryLogRepository = inventoryLogRepository;
    }

    @Transactional
    public Delivery createDelivery(Delivery delivery, List<DeliveryItem> items) {
        // Set client
        Client client = clientRepository.findById(delivery.getClient().getId()).orElse(null);
        if (client == null) {
            throw new RuntimeException("Client not found");
        }
        delivery.setClient(client);

        // Generate invoice number
        delivery.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        delivery.setCreatedAt(new Date());
        delivery.setUpdatedAt(new Date());

        // Calculate totals
        BigDecimal subtotal = BigDecimal.ZERO;
        for (DeliveryItem item : items) {
            PearlStock pearlStock = pearlStockRepository.findById(item.getPearlStock().getId()).orElse(null);
            if (pearlStock == null || pearlStock.getQuantity() < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock for pearl stock ID: " + item.getPearlStock().getId());
            }

            item.setUnitPrice(pearlStock.getPricePerUnit());
            item.setTotalPrice(pearlStock.getPricePerUnit().multiply(BigDecimal.valueOf(item.getQuantity())));
            subtotal = subtotal.add(item.getTotalPrice());

            // Update stock
            pearlStock.setQuantity(pearlStock.getQuantity() - item.getQuantity());
            pearlStockRepository.save(pearlStock);

            // Log inventory change
            InventoryLog log = new InventoryLog();
            log.setPearlStock(pearlStock);
            log.setQuantityChange(-item.getQuantity());
            log.setChangeType(InventoryLog.ChangeType.SALE);
            log.setReferenceId(delivery.getId());
            log.setReferenceType("DELIVERY");
            log.setNotes("Sold to client: " + client.getName());
            log.setCreatedAt(new Date());
            inventoryLogRepository.save(log);
        }

        delivery.setSubtotal(subtotal);
        // Assuming 10% tax for example
        delivery.setTaxAmount(subtotal.multiply(BigDecimal.valueOf(0.10)));
        delivery.setTotalAmount(subtotal.add(delivery.getTaxAmount()));

        Delivery savedDelivery = deliveryRepository.save(delivery);

        // Save items
        for (DeliveryItem item : items) {
            item.setDelivery(savedDelivery);
            deliveryItemRepository.save(item);
        }

        return savedDelivery;
    }

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    public Delivery getDeliveryById(Long id) {
        return deliveryRepository.findById(id).orElse(null);
    }

    public List<DeliveryItem> getDeliveryItems(Long deliveryId) {
        return deliveryItemRepository.findByDeliveryId(deliveryId);
    }
}