package com.develhope.spring.daos;

import com.develhope.spring.models.entities.AddressEntity;
import com.develhope.spring.models.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDao extends JpaRepository<AddressEntity, String> {
}
