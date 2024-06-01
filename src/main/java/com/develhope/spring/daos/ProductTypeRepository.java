package com.develhope.spring.daos;

import com.develhope.spring.models.entities.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//cambia nome
@Repository
public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, String> {
}
