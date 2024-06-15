package com.develhope.spring.daos;

import com.develhope.spring.models.entities.RestaurantEntity;
import com.develhope.spring.models.entities.RestaurantTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Repository
public interface RestaurantDao extends JpaRepository<RestaurantEntity, String> {

//    @Query(value = "SELECT r FROM RestaurantEntity r WHERE r.id_restaurant = :id")
//    Optional<RestaurantEntity> findById(String id);
//    @Modifying
//    @Query("DELETE FROM RestaurantEntity r WHERE r.id_restaurant = :id")
//    int deleteById(String id);

    //List<RestaurantEntity> findByRestaurantTypeEntityIn(Collection<RestaurantTypeEntity> restaurantTypeEntities);

    @Query("SELECT r FROM RestaurantEntity r JOIN r.restaurantTypeEntities rt WHERE rt.typeName = :typeName")
    List<RestaurantEntity> findByRestaurantTypeEntityIn(@Param("typeName") String typeName);

    Optional<RestaurantEntity> findByRestaurantNameIgnoreCase(String RestaurantName);
    List<RestaurantEntity> findByIsDeliveryAvailableTrue();
    List<RestaurantEntity> findByIsTakeAwayAvailableTrue();
    List<RestaurantEntity> findByIsTakeAwayAvailableTrueOrIsDeliveryAvailableTrue();
}
