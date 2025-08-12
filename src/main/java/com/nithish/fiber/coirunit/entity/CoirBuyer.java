package com.nithish.fiber.coirunit.entity;



import com.nithish.fiber.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "coir_buyers_dtl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoirBuyer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coir_buyer_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gst_no", nullable = false)
    private String gstNo;

    @Column(name = "mobile_no")
    private String mobileNo;


}
