package com.develhope.spring.daos;

import com.develhope.spring.models.entities.CartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductDao extends JpaRepository<CartProductEntity, String> {
}