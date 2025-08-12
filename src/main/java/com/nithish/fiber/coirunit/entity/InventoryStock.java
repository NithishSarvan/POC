package com.nithish.fiber.coirunit.entity;



import com.nithish.fiber.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryStock extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long id;

    @Column(name = "stock_date", nullable = false)
    private LocalDate stockDate;

    @Column(name = "coir_type", nullable = false)
    private String coirType;

    @Column(nullable = false)
    private Double quantity;

    @Column(name = "unit_name", nullable = false)
    private String unitName;

    @Column
    private String remarks;


}
