package com.develhope.spring.daos;

import com.develhope.spring.models.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDao extends JpaRepository<PaymentEntity, String> {
}
