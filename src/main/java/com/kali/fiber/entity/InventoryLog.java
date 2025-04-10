package com.kali.fiber.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "inventory_log")
@Data
public class InventoryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pearl_stock_id")
    private PearlStock pearlStock;

    private Integer quantityChange;

    @Enumerated(EnumType.STRING)
    private ChangeType changeType;

    private Long referenceId;
    private String referenceType;
    private String notes;
    private Date createdAt;

    public enum ChangeType {
        PURCHASE, SALE, ADJUSTMENT
    }

    // Getters and Setters
}