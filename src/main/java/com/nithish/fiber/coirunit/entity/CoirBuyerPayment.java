package com.nithish.fiber.coirunit.entity;


import com.nithish.fiber.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "coir_buyer_payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoirBuyerPayment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coir_buyer_id", nullable = false)
    private CoirBuyer buyer;

    @Column(name = "invoice_no", nullable = false)
    private String invoiceNo;

    @Column(name = "invoice_date", nullable = false)
    private LocalDate invoiceDate;

    @Column(name = "coir_type", nullable = false)
    private String coirType;

    @Column(nullable = false)
    private Double quantity;

    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    @Column(nullable = false)
    private Double subtotal;

    @Column(name = "gst_percentage")
    private Double gstPercentage = 0.0;

    @Column(name = "gst_amount")
    private Double gstAmount = 0.0;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Column(name = "advance_paid")
    private Double advancePaid = 0.0;

    @Column(name = "outstanding_amount")
    private Double outstandingAmount = 0.0;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.Pending;

    public enum PaymentStatus { Pending, Partial, Paid }

    @Column
    private String remarks;


}
