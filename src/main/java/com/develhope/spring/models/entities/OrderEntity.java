package com.develhope.spring.models.entities;

import com.develhope.spring.utils.OrderStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private OrderStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;


    public OrderEntity() {
    }

    public OrderEntity(String id, OrderStatus status, CustomerEntity customer) {
        this.id = id;
        this.status = status;
        this.customer = customer;
    }
}