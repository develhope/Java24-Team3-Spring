package com.develhope.spring.dao;

import com.develhope.spring.models.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantDao extends JpaRepository<RestaurantEntity, Long> {
}
