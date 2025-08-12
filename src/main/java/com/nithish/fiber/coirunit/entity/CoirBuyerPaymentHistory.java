package com.nithish.fiber.coirunit.entity;


import com.nithish.fiber.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "coir_buyer_payment_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoirBuyerPaymentHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", nullable = false)
    private CoirBuyerPayment buyerPayment;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Column(name = "amount_paid", nullable = false)
    private Double amountPaid;

    @Column(name = "payment_mode")
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode = PaymentMode.Cash;

    public enum PaymentMode { Cash, Bank_Transfer, Cheque, UPI }

    @Column(name = "reference_no")
    private String referenceNo;

    @Column
    private String remarks;

}

