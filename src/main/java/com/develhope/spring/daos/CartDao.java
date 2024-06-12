package com.develhope.spring.daos;

import com.develhope.spring.models.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDao extends JpaRepository<CartEntity, String> {
}
