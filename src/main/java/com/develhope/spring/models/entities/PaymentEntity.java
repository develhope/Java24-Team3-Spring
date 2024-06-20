package com.develhope.spring.models.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private PaymentMethod method;

    @Column(nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;

    @Column(name = "total", nullable = false)
    private BigDecimal totalPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    public PaymentEntity() {
    }

    public PaymentEntity(PaymentMethod method, PaymentStatus status, BigDecimal totalPrice, OrderEntity order) {
        this.method = method;
        this.status = status;
        this.totalPrice = totalPrice;
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

}