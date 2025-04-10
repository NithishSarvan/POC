package com.kali.fiber.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "dryness_process")
@Data
public class DrynessProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private Date processDate;
    private Integer quantity;
    private BigDecimal advanceAmount;
    private BigDecimal totalAmount;
    private BigDecimal pricePerUnit;
    private BigDecimal pendingAmount;

    @Enumerated(EnumType.STRING)
    private ProcessStatus status;
    private Date createdAt;
    private Date updatedAt;

    public enum ProcessStatus {
        IN_PROGRESS, COMPLETED
    }

    // Getters and Setters
}