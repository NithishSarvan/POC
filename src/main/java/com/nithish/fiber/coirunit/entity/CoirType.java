package com.nithish.fiber.coirunit.entity;


import com.nithish.fiber.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "coir_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoirType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coir_type_id")
    private Long id;

    @Column(name = "type_name", nullable = false)
    private String typeName;

    @Column(name = "sub_type")
    private String subType;

    @Column(name = "rate_per_unit", nullable = false)
    private Double ratePerUnit;

    @Column(name = "unit_name", nullable = false)
    private String unitName;

    @Column(name = "is_active")
    private Boolean isActive = true;


}
