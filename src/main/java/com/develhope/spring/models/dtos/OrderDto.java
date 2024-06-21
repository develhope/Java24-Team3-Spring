package com.develhope.spring.models.dtos;

package com.develhope.spring.models.entities;

import com.develhope.spring.models.entities.CustomerEntity;
import com.develhope.spring.utils.OrderStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "order")
public class OrderDto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private OrderStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;



}