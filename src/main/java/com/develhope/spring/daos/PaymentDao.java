package com.develhope.spring.daos;

import com.develhope.spring.models.entities.PaymentEntity;
import com.develhope.spring.models.entities.PaymentMethod;
import com.develhope.spring.models.entities.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentDao extends JpaRepository<PaymentEntity, String> {

    Optional<PaymentEntity> findByOrderId(String orderId);

    List<PaymentEntity> findByStatus(PaymentStatus status);

    List<PaymentEntity> findByMethod(PaymentMethod method);

}