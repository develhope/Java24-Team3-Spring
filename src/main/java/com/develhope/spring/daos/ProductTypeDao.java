package com.develhope.spring.daos;

import com.develhope.spring.models.entities.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeDao extends JpaRepository<ProductTypeEntity, String> {

    ProductTypeEntity findByProductType(String productType);

    void deleteByProductType(String productType);
}
