package com.develhope.spring.daos;

import com.develhope.spring.models.entities.RestaurantTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantTypeDao extends JpaRepository<RestaurantTypeEntity, String> {
}
