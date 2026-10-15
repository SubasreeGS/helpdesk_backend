package com.springboot.helpdesk.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="customer_plan")
public class CustomerPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="activation_date")

    private LocalDate activationDate;
    @Column(name="end_date")
    private LocalDate endDate;

    private String coupon;
    @Column(name="amount_paid",nullable=false)
    private double amountPaid=0.0;

    @ManyToOne
    @JoinColumn(name="customer_id",nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="plan_id",nullable = false)
    private Plan plan;
}
