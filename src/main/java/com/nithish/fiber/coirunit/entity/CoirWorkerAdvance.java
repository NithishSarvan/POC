package com.nithish.fiber.coirunit.entity;



import com.nithish.fiber.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "coir_worker_advance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoirWorkerAdvance extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advance_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coir_worker_id", nullable = false)
    private CoirWorker worker;

    @Column(name = "advance_date", nullable = false)
    private LocalDate advanceDate;

    @Column(name = "is_long_term_advance")
    private Boolean isLongTermAdvance = false;

    @Column(nullable = false)
    private Double amount;

    @Column
    private String remarks;


}
