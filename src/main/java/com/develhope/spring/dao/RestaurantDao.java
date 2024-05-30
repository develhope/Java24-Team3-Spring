package com.develhope.spring.dao;

import com.develhope.spring.models.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RestaurantDao extends JpaRepository<RestaurantEntity, Long> {

    List<RestaurantEntity> findByIsDeliveryAvaibleTrue();
    List<RestaurantEntity> findByIsTakeAwayAvaibleTrue();
    List<RestaurantEntity> findByIsTakeAwayAvaibleTrueOrIsDeliveryAvaibleTrue();
}
