package com.develhope.spring.daos;

import com.develhope.spring.models.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<ProductEntity, String> {

    List<ProductEntity> findProductByName(String name);

    List<ProductEntity> findProductByPriceBetween(BigDecimal lowerBoundPrice, BigDecimal upperBoundPrice);
}
