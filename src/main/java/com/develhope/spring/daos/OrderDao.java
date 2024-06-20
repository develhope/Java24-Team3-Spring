package com.develhope.spring.daos;

import com.develhope.spring.models.entities.OrderEntity;
import com.develhope.spring.utils.OrderStatus;
import com.develhope.spring.utils.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<OrderEntity, String> {

    List<OrderEntity> findByCreationDateAfter(LocalDateTime dateTime);

    List<OrderEntity> findByCreationDateBefore(LocalDateTime dateTime);

    List<OrderEntity> findByCreationDateBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<OrderEntity> findByStatus(OrderStatus status);

    List<OrderEntity> findByType(OrderType type);

}