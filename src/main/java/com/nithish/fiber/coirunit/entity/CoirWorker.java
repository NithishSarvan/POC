package com.nithish.fiber.coirunit.entity;



import com.nithish.fiber.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "coir_worker_dtl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoirWorker extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coir_worker_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "join_date", nullable = false)
    private LocalDate joinDate;

    @Column(name = "aadhar_no")
    private String aadharNo;

    @Column(length = 500)
    private String address;

    @Column
    @Enumerated(EnumType.STRING)
    private WorkerStatus status = WorkerStatus.Active;

    public enum WorkerStatus { Active, Inactive }

}
