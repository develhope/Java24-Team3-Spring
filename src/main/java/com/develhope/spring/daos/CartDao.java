package com.develhope.spring.daos;

import com.develhope.spring.models.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartDao extends JpaRepository<CartEntity, String> {

    //Optional<CartEntity> findCartByCustomerId();

}
