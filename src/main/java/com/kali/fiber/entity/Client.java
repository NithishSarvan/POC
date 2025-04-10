package com.kali.fiber.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "client")
@Data
public class Client {
    // dryness procees merchant(velila matta sora kaya vechi kudukaravanga details)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String gstNumber;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;
    private Date createdAt;
    private Date updatedAt;

    // Getters and Setters
}
