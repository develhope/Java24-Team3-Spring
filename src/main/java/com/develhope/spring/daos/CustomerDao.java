package com.develhope.spring.daos;

import com.develhope.spring.models.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerDao extends JpaRepository<CustomerEntity, String> {

    Optional<CustomerEntity> findCustomerByEmail(String email);

    List<CustomerEntity> findCustomerByIsDeleted(Boolean isDeleted);

    List<CustomerEntity> findCustomerByIsVerified(Boolean isVerified);
}
