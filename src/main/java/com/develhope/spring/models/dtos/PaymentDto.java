package com.develhope.spring.models.dtos;

import com.develhope.spring.models.entities.PaymentMethod;
import com.develhope.spring.models.entities.PaymentStatus;

import java.math.BigDecimal;

public class PaymentDto {

    private String id;

    private PaymentMethod method;

    private PaymentStatus status = PaymentStatus.PENDING;

    private String orderId;

    public PaymentDto() {
    }

    public PaymentDto(String id, PaymentMethod method, PaymentStatus status, String orderId) {
        this.id = id;
        this.method = method;
        this.status = status;
        this.orderId = orderId;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}