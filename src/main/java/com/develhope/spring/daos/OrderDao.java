package com.develhope.spring.daos;

import com.develhope.spring.models.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<OrderEntity, String> {



}