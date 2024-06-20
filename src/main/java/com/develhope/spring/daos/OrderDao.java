package com.develhope.spring.daos;

import com.develhope.spring.models.entities.OrderEntity;
import com.develhope.spring.utils.OrderStatus;
import com.develhope.spring.utils.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDao extends JpaRepository<OrderEntity, String> {

    List<OrderEntity> findByCustomerId(String customerId);

    Optional<OrderEntity> findByPaymentId(String paymentId);

    List<OrderEntity> findByRestaurantId(String restaurantId);

    List<OrderEntity> findByStatus(OrderStatus status);

    List<OrderEntity> findByType(OrderType type);

    List<OrderEntity> findByCreationDateAfter(LocalDateTime dateTime);

    List<OrderEntity> findByCreationDateBefore(LocalDateTime dateTime);

    List<OrderEntity> findByCreationDateBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

}