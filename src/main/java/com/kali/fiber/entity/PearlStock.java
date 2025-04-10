package com.kali.fiber.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "pearl_stock")
@Data
public class PearlStock {

    // to maintain coco peat stock
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    private String vehicleDetails;
    private Date purchaseDate;
    private Integer quantity;
    private BigDecimal advanceAmount;
    private BigDecimal totalAmount;
    private BigDecimal pendingAmount;

    @Enumerated(EnumType.STRING)
    private StockType stockType;

    private Boolean isDry;
    private Date createdAt;
    private Date updatedAt;
    private BigDecimal pricePerUnit;



    public enum StockType {
        OWN, OUT
    }

    // Getters and Setters
}