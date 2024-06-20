package com.develhope.spring.daos;

import com.develhope.spring.models.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends JpaRepository<CartEntity, String> {

}
