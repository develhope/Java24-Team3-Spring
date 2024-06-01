package com.develhope.spring.daos;

import com.develhope.spring.models.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//cambia nome
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}
