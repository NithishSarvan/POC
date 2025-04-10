package com.kali.fiber.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "labor")
@Data
public class Labor {
    // block adikra labours
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private LaborType type;

    private String phone;
    private String address;
    private BigDecimal hourlyRate;
    private BigDecimal blockRate;
    private Date createdAt;
    private Date updatedAt;

    public enum LaborType {
        PERMANENT, TEMPORARY
    }

    // Getters and Setters
}