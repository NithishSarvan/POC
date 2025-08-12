package com.nithish.fiber.coirunit.entity;


import com.nithish.fiber.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "coir_worker_wage_entry")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoirWorkerWageEntry extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wage_entry_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coir_worker_id", nullable = false)
    private CoirWorker worker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coir_type_id", nullable = false)
    private CoirType coirType;

    @Column(name = "work_date", nullable = false)
    private LocalDate workDate;

    @Column(nullable = false)
    private Double quantity;

    @Column(name = "rate_per_unit", nullable = false)
    private Double ratePerUnit;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Column
    private String remarks;

}
