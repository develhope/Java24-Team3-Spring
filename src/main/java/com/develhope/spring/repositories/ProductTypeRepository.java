package com.develhope.spring.repositories;

import com.develhope.spring.entities.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, UUID> {
}
