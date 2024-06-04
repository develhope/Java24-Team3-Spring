package com.develhope.spring.daos;

import com.develhope.spring.models.entities.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeDao extends JpaRepository<ProductTypeEntity, Long> {

    List<ProductTypeEntity> findByProductType(String productType);

}
