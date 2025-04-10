package com.kali.fiber.controller;

import com.kali.fiber.entity.Delivery;
import com.kali.fiber.entity.DeliveryItem;
import com.kali.fiber.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public Delivery createDelivery(@RequestBody Delivery delivery, @RequestBody List<DeliveryItem> items) {
        return deliveryService.createDelivery(delivery, items);
    }

    @GetMapping
    public List<Delivery> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable Long id) {
        Delivery delivery = deliveryService.getDeliveryById(id);
        return delivery != null ? ResponseEntity.ok(delivery) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/items")
    public List<DeliveryItem> getDeliveryItems(@PathVariable Long id) {
        return deliveryService.getDeliveryItems(id);
    }
}