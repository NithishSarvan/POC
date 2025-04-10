package com.kali.fiber.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "labor_wages")
@Data
public class LaborWages {

    // block adikira salary maintainence
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "labor_id")
    private Labor labor;

    @Enumerated(EnumType.STRING)
    private CalculationType calculationType;

    private Double hoursWorked;
    private Integer blocksCompleted;
    private BigDecimal amount;
    private BigDecimal advancePaid;
    private BigDecimal pendingAmount;
    private Date paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private Date createdAt;
    private Date updatedAt;
    private PaymentStatus paymentStatus;

    public enum CalculationType {
        HOURLY, BLOCK
    }

    public enum PaymentStatus {
        PENDING, PARTIAL, PAID
    }

    // Getters and Setters
}