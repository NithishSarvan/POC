package com.nithish.fiber.coirunit.entity;



import com.nithish.fiber.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "coir_worker_payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoirWorkerPayment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coir_worker_id", nullable = false)
    private CoirWorker worker;

    @Column(name = "week_start_date", nullable = false)
    private LocalDate weekStartDate;

    @Column(name = "week_end_date", nullable = false)
    private LocalDate weekEndDate;

    @Column(name = "total_wages", nullable = false)
    private Double totalWages;

    @Column(name = "total_advance")
    private Double totalAdvance = 0.0;

    @Column(name = "carry_forward")
    private Double carryForward = 0.0;

    @Column(name = "paid_amount")
    private Double paidAmount = 0.0;

    @Column
    private String remarks;


}
